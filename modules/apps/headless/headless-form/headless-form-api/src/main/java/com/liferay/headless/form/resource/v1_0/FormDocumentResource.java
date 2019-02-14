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

package com.liferay.headless.form.resource.v1_0;

import com.liferay.headless.form.dto.v1_0.FormDocument;
import com.liferay.oauth2.provider.scope.RequiresScope;

import javax.annotation.Generated;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-form/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@Path("/v1.0")
public interface FormDocumentResource {

	@DELETE
	@Path("/form-documents/{form-document-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Response deleteFormDocument( @PathParam("form-document-id") Long formDocumentId ) throws Exception;

	@GET
	@Path("/form-documents/{form-document-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public FormDocument getFormDocument( @PathParam("form-document-id") Long formDocumentId ) throws Exception;

}