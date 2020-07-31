/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.file.install.internal.properties;

import com.liferay.petra.string.CharPool;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Matthew Tambara
 */
public class TypedProperties extends AbstractMap<String, Object> {

	public TypedProperties(SubstitutionalCallback substitutionalCallback) {
		_substitutionalCallback = substitutionalCallback;
	}

	@Override
	public void clear() {
		_storage.clear();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return new AbstractSet<Entry<String, Object>>() {

			@Override
			public Iterator<Entry<String, Object>> iterator() {
				return new KeyIterator();
			}

			@Override
			public int size() {
				return _storage.size();
			}

		};
	}

	@Override
	public Object get(Object key) {
		String value = _storage.get(key);

		if ((value != null) && _storage.isTyped()) {
			return _convertFromString(value);
		}

		return value;
	}

	public void load(Reader reader) throws IOException {
		_storage.loadLayout(reader);

		_substitute(_substitutionalCallback);
	}

	@Override
	public Object put(String key, Object value) {
		if ((value instanceof String) && !_storage.isTyped()) {
			return _storage.put(key, (String)value);
		}

		_ensureTyped();

		String old = _storage.put(key, _convertToString(value));

		if (old == null) {
			return null;
		}

		return _convertFromString(old);
	}

	@Override
	public Object remove(Object key) {
		return _storage.remove(key);
	}

	public void save(Writer writer) throws IOException {
		_storage.save(writer);
	}

	private static Object _convertFromString(String value) {
		try {
			return ConfigurationHandler.read(value);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private static String _convertToString(Object value) {
		try {
			return ConfigurationHandler.write(value);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private void _ensureTyped() {
		if (!_storage.isTyped()) {
			_storage.setTyped(true);

			Set<String> keys = new HashSet<>(_storage.keySet());

			for (String key : keys) {
				String string = _convertToString(_storage.get(key));

				_storage.put(
					key, _storage.getComments(key),
					Arrays.asList(string.split("\n")));
			}
		}
	}

	private void _substitute(SubstitutionalCallback substitutionCallback) {
		if (substitutionCallback == null) {
			substitutionCallback = _defaultSubstitutionCallback;
		}

		DynamicMap dynamic = new DynamicMap(_storage, substitutionCallback);

		_storage.putAllSubstituted(dynamic);
	}

	private static final String _ENV_PREFIX = "env:";

	private final SubstitutionalCallback _defaultSubstitutionCallback =
		value -> {
			if (value.startsWith(_ENV_PREFIX)) {
				return System.getenv(value.substring(_ENV_PREFIX.length()));
			}

			return System.getProperty(value);
		};

	private final Properties _storage = new Properties();
	private final SubstitutionalCallback _substitutionalCallback;

	private static class DynamicMap extends AbstractMap<String, String> {

		public DynamicMap(
			Properties properties,
			SubstitutionalCallback substitutionCallback) {

			_properties = properties;
			_substitutionCallback = substitutionCallback;
		}

		@Override
		public Set<Entry<String, String>> entrySet() {
			return new AbstractSet<Entry<String, String>>() {

				@Override
				public Iterator<Entry<String, String>> iterator() {
					Set<String> keys = _properties.keySet();

					return new ComputedIterator(keys.iterator());
				}

				@Override
				public int size() {
					return _properties.size();
				}

			};
		}

		private String _compute(final String key) {
			return InterpolationUtil.substVars(
				_properties.get(key), key, _cycles, this,
				value -> {
					String string = DynamicMap.this.get(value);

					if (string == null) {
						return _substitutionCallback.getValue(value);
					}

					if (!_properties.isTyped()) {
						return string;
					}

					boolean mult = false;

					boolean hasType = false;

					char t = string.charAt(0);

					if ((t == CharPool.OPEN_BRACKET) ||
						(t == CharPool.OPEN_PARENTHESIS)) {

						mult = true;
					}
					else {
						t = string.charAt(1);

						if ((t == CharPool.OPEN_BRACKET) ||
							(t == CharPool.OPEN_PARENTHESIS)) {

							mult = true;
						}

						hasType = true;
					}

					if (mult) {
						throw new IllegalArgumentException(
							"Cannot substitute from a collection/array " +
								"value: " + value);
					}

					if (hasType) {
						return (String)_convertFromString(string.substring(1));
					}

					return (String)_convertFromString(string);
				},
				false);
		}

		private final Map<String, String> _cycles = new HashMap<>();
		private final Properties _properties;
		private final SubstitutionalCallback _substitutionCallback;

		private class ComputedIterator
			implements Iterator<Entry<String, String>> {

			public ComputedIterator(Iterator<String> iterator) {
				_iterator = iterator;
			}

			@Override
			public boolean hasNext() {
				return _iterator.hasNext();
			}

			@Override
			public Entry<String, String> next() {
				String key = _iterator.next();

				return new Entry<String, String>() {

					@Override
					public String getKey() {
						return key;
					}

					@Override
					public String getValue() {
						return _compute(key);
					}

					@Override
					public String setValue(String value) {
						throw new UnsupportedOperationException();
					}

				};
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			private final Iterator<String> _iterator;

		}

	}

	private class KeyIterator implements Iterator<Entry<String, Object>> {

		public KeyIterator() {
			Set<String> entries = _storage.keySet();

			_iterator = entries.iterator();
		}

		@Override
		public boolean hasNext() {
			return _iterator.hasNext();
		}

		@Override
		public Entry<String, Object> next() {
			String key = _iterator.next();

			return new Entry<String, Object>() {

				@Override
				public String getKey() {
					return key;
				}

				@Override
				public Object getValue() {
					return TypedProperties.this.get(key);
				}

				@Override
				public Object setValue(Object value) {
					return TypedProperties.this.put(key, value);
				}

			};
		}

		@Override
		public void remove() {
			_iterator.remove();
		}

		private final Iterator<String> _iterator;

	}

}