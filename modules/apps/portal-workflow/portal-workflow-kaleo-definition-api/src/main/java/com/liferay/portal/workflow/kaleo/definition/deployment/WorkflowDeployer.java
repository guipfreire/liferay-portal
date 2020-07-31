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

package com.liferay.portal.workflow.kaleo.definition.deployment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.workflow.kaleo.definition.Definition;

/**
 * @author Michael C. Han
 */
public interface WorkflowDeployer {

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #deploy(String,
	 *             String, String, Definition, ServiceContext)}
	 */
	@Deprecated
	public default WorkflowDefinition deploy(
			String title, String name, Definition definition,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public WorkflowDefinition deploy(
			String title, String name, String scope, Definition definition,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #save(String,
	 *             String, String, Definition, ServiceContext)}
	 */
	@Deprecated
	public default WorkflowDefinition save(
			String title, String name, Definition definition,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public WorkflowDefinition save(
			String title, String name, String scope, Definition definition,
			ServiceContext serviceContext)
		throws PortalException;

}