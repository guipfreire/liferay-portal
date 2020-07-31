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

package com.liferay.info.item;

import java.util.Optional;

/**
 * @author Jürgen Kappler
 */
public class InfoItemReference {

	public static final String VERSION_LATEST = "VERSION_LATEST";

	public static final String VERSION_LATEST_APPROVED =
		"VERSION_LATEST_APPROVED";

	public InfoItemReference(long classPK) {
		_classPK = classPK;
	}

	public long getClassPK() {
		return _classPK;
	}

	public Optional<String> getVersionOptional() {
		return Optional.ofNullable(_version);
	}

	public void setVersion(String version) {
		_version = version;
	}

	private final long _classPK;
	private String _version;

}