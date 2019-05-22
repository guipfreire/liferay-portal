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

package com.liferay.document.library.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.model.DLFileEntryPreview;
import com.liferay.document.library.model.DLFileEntryPreviewModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the DLFileEntryPreview service. Represents a row in the &quot;DLFileEntryPreview&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>DLFileEntryPreviewModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileEntryPreviewImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryPreviewImpl
 * @generated
 */
@ProviderType
public class DLFileEntryPreviewModelImpl
	extends BaseModelImpl<DLFileEntryPreview>
	implements DLFileEntryPreviewModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dl file entry preview model instance should use the <code>DLFileEntryPreview</code> interface instead.
	 */
	public static final String TABLE_NAME = "DLFileEntryPreview";

	public static final Object[][] TABLE_COLUMNS = {
		{"fileEntryPreviewId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT}, {"fileVersionId", Types.BIGINT},
		{"previewType", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("fileEntryPreviewId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("previewType", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DLFileEntryPreview (fileEntryPreviewId LONG not null primary key,groupId LONG,fileEntryId LONG,fileVersionId LONG,previewType INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table DLFileEntryPreview";

	public static final String ORDER_BY_JPQL =
		" ORDER BY dlFileEntryPreview.fileEntryPreviewId DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DLFileEntryPreview.fileEntryPreviewId DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.document.library.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.document.library.model.DLFileEntryPreview"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.document.library.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.document.library.model.DLFileEntryPreview"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.document.library.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.document.library.model.DLFileEntryPreview"),
		true);

	public static final long FILEENTRYID_COLUMN_BITMASK = 1L;

	public static final long FILEVERSIONID_COLUMN_BITMASK = 2L;

	public static final long PREVIEWTYPE_COLUMN_BITMASK = 4L;

	public static final long FILEENTRYPREVIEWID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.document.library.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.document.library.model.DLFileEntryPreview"));

	public DLFileEntryPreviewModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryPreviewId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFileEntryPreviewId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fileEntryPreviewId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileEntryPreview.class;
	}

	@Override
	public String getModelClassName() {
		return DLFileEntryPreview.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DLFileEntryPreview, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DLFileEntryPreview, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryPreview, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DLFileEntryPreview)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DLFileEntryPreview, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DLFileEntryPreview, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DLFileEntryPreview)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DLFileEntryPreview, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DLFileEntryPreview, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<DLFileEntryPreview, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DLFileEntryPreview, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DLFileEntryPreview, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<DLFileEntryPreview, Object>>();
		Map<String, BiConsumer<DLFileEntryPreview, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<DLFileEntryPreview, ?>>();

		attributeGetterFunctions.put(
			"fileEntryPreviewId",
			new Function<DLFileEntryPreview, Object>() {

				@Override
				public Object apply(DLFileEntryPreview dlFileEntryPreview) {
					return dlFileEntryPreview.getFileEntryPreviewId();
				}

			});
		attributeSetterBiConsumers.put(
			"fileEntryPreviewId",
			new BiConsumer<DLFileEntryPreview, Object>() {

				@Override
				public void accept(
					DLFileEntryPreview dlFileEntryPreview,
					Object fileEntryPreviewId) {

					dlFileEntryPreview.setFileEntryPreviewId(
						(Long)fileEntryPreviewId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<DLFileEntryPreview, Object>() {

				@Override
				public Object apply(DLFileEntryPreview dlFileEntryPreview) {
					return dlFileEntryPreview.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<DLFileEntryPreview, Object>() {

				@Override
				public void accept(
					DLFileEntryPreview dlFileEntryPreview, Object groupId) {

					dlFileEntryPreview.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"fileEntryId",
			new Function<DLFileEntryPreview, Object>() {

				@Override
				public Object apply(DLFileEntryPreview dlFileEntryPreview) {
					return dlFileEntryPreview.getFileEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"fileEntryId",
			new BiConsumer<DLFileEntryPreview, Object>() {

				@Override
				public void accept(
					DLFileEntryPreview dlFileEntryPreview, Object fileEntryId) {

					dlFileEntryPreview.setFileEntryId((Long)fileEntryId);
				}

			});
		attributeGetterFunctions.put(
			"fileVersionId",
			new Function<DLFileEntryPreview, Object>() {

				@Override
				public Object apply(DLFileEntryPreview dlFileEntryPreview) {
					return dlFileEntryPreview.getFileVersionId();
				}

			});
		attributeSetterBiConsumers.put(
			"fileVersionId",
			new BiConsumer<DLFileEntryPreview, Object>() {

				@Override
				public void accept(
					DLFileEntryPreview dlFileEntryPreview,
					Object fileVersionId) {

					dlFileEntryPreview.setFileVersionId((Long)fileVersionId);
				}

			});
		attributeGetterFunctions.put(
			"previewType",
			new Function<DLFileEntryPreview, Object>() {

				@Override
				public Object apply(DLFileEntryPreview dlFileEntryPreview) {
					return dlFileEntryPreview.getPreviewType();
				}

			});
		attributeSetterBiConsumers.put(
			"previewType",
			new BiConsumer<DLFileEntryPreview, Object>() {

				@Override
				public void accept(
					DLFileEntryPreview dlFileEntryPreview, Object previewType) {

					dlFileEntryPreview.setPreviewType((Integer)previewType);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getFileEntryPreviewId() {
		return _fileEntryPreviewId;
	}

	@Override
	public void setFileEntryPreviewId(long fileEntryPreviewId) {
		_columnBitmask = -1L;

		_fileEntryPreviewId = fileEntryPreviewId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_columnBitmask |= FILEENTRYID_COLUMN_BITMASK;

		if (!_setOriginalFileEntryId) {
			_setOriginalFileEntryId = true;

			_originalFileEntryId = _fileEntryId;
		}

		_fileEntryId = fileEntryId;
	}

	public long getOriginalFileEntryId() {
		return _originalFileEntryId;
	}

	@Override
	public long getFileVersionId() {
		return _fileVersionId;
	}

	@Override
	public void setFileVersionId(long fileVersionId) {
		_columnBitmask |= FILEVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalFileVersionId) {
			_setOriginalFileVersionId = true;

			_originalFileVersionId = _fileVersionId;
		}

		_fileVersionId = fileVersionId;
	}

	public long getOriginalFileVersionId() {
		return _originalFileVersionId;
	}

	@Override
	public int getPreviewType() {
		return _previewType;
	}

	@Override
	public void setPreviewType(int previewType) {
		_columnBitmask |= PREVIEWTYPE_COLUMN_BITMASK;

		if (!_setOriginalPreviewType) {
			_setOriginalPreviewType = true;

			_originalPreviewType = _previewType;
		}

		_previewType = previewType;
	}

	public int getOriginalPreviewType() {
		return _originalPreviewType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, DLFileEntryPreview.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLFileEntryPreview toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DLFileEntryPreview)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DLFileEntryPreviewImpl dlFileEntryPreviewImpl =
			new DLFileEntryPreviewImpl();

		dlFileEntryPreviewImpl.setFileEntryPreviewId(getFileEntryPreviewId());
		dlFileEntryPreviewImpl.setGroupId(getGroupId());
		dlFileEntryPreviewImpl.setFileEntryId(getFileEntryId());
		dlFileEntryPreviewImpl.setFileVersionId(getFileVersionId());
		dlFileEntryPreviewImpl.setPreviewType(getPreviewType());

		dlFileEntryPreviewImpl.resetOriginalValues();

		return dlFileEntryPreviewImpl;
	}

	@Override
	public int compareTo(DLFileEntryPreview dlFileEntryPreview) {
		int value = 0;

		if (getFileEntryPreviewId() <
				dlFileEntryPreview.getFileEntryPreviewId()) {

			value = -1;
		}
		else if (getFileEntryPreviewId() >
					dlFileEntryPreview.getFileEntryPreviewId()) {

			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DLFileEntryPreview)) {
			return false;
		}

		DLFileEntryPreview dlFileEntryPreview = (DLFileEntryPreview)obj;

		long primaryKey = dlFileEntryPreview.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		DLFileEntryPreviewModelImpl dlFileEntryPreviewModelImpl = this;

		dlFileEntryPreviewModelImpl._originalFileEntryId =
			dlFileEntryPreviewModelImpl._fileEntryId;

		dlFileEntryPreviewModelImpl._setOriginalFileEntryId = false;

		dlFileEntryPreviewModelImpl._originalFileVersionId =
			dlFileEntryPreviewModelImpl._fileVersionId;

		dlFileEntryPreviewModelImpl._setOriginalFileVersionId = false;

		dlFileEntryPreviewModelImpl._originalPreviewType =
			dlFileEntryPreviewModelImpl._previewType;

		dlFileEntryPreviewModelImpl._setOriginalPreviewType = false;

		dlFileEntryPreviewModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLFileEntryPreview> toCacheModel() {
		DLFileEntryPreviewCacheModel dlFileEntryPreviewCacheModel =
			new DLFileEntryPreviewCacheModel();

		dlFileEntryPreviewCacheModel.fileEntryPreviewId =
			getFileEntryPreviewId();

		dlFileEntryPreviewCacheModel.groupId = getGroupId();

		dlFileEntryPreviewCacheModel.fileEntryId = getFileEntryId();

		dlFileEntryPreviewCacheModel.fileVersionId = getFileVersionId();

		dlFileEntryPreviewCacheModel.previewType = getPreviewType();

		return dlFileEntryPreviewCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DLFileEntryPreview, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DLFileEntryPreview, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryPreview, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DLFileEntryPreview)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<DLFileEntryPreview, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DLFileEntryPreview, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DLFileEntryPreview, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DLFileEntryPreview)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		DLFileEntryPreview.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		DLFileEntryPreview.class, ModelWrapper.class
	};

	private long _fileEntryPreviewId;
	private long _groupId;
	private long _fileEntryId;
	private long _originalFileEntryId;
	private boolean _setOriginalFileEntryId;
	private long _fileVersionId;
	private long _originalFileVersionId;
	private boolean _setOriginalFileVersionId;
	private int _previewType;
	private int _originalPreviewType;
	private boolean _setOriginalPreviewType;
	private long _columnBitmask;
	private DLFileEntryPreview _escapedModel;

}