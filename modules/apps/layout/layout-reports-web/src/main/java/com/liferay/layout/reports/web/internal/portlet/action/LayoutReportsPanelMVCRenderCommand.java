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

package com.liferay.layout.reports.web.internal.portlet.action;

import com.liferay.layout.reports.web.internal.constants.LayoutReportsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sarai Díaz
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LayoutReportsPortletKeys.LAYOUT_REPORTS,
		"mvc.command.name=/layout_reports/layout_reports_panel"
	},
	service = MVCRenderCommand.class
)
public class LayoutReportsPanelMVCRenderCommand implements MVCRenderCommand {

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		return "/layout_reports_panel.jsp";
	}

}