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

package com.liferay.portal.security.sso.openid.connect;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface OpenIdConnectProvider<S, T> {

	public String getClientId();

	public String getClientSecret();

	public String getName();

	public S getOIDCClientMetadata();

	public T getOIDCProviderMetadata()
		throws OpenIdConnectServiceException.ProviderException;

	public default int geTokenConnectionTimeout() {
		return 500;
	}

	public String getScopes();

}