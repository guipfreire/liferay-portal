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

import com.liferay.headless.form.dto.v1_0.FormRecord;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

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
public interface FormRecordResource {

	@GET
	@Path("/form-records/{form-record-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public FormRecord getFormRecord( @PathParam("form-record-id") Long formRecordId ) throws Exception;

	@Consumes("application/json")
	@PUT
	@Path("/form-records/{form-record-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public FormRecord putFormRecord( @PathParam("form-record-id") Long formRecordId , FormRecord formRecord ) throws Exception;

	@GET
	@Path("/forms/{form-id}/form-records")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Page<FormRecord> getFormFormRecordsPage( @PathParam("form-id") Long formId , @Context Pagination pagination ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/forms/{form-id}/form-records")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public FormRecord postFormFormRecord( @PathParam("form-id") Long formId , FormRecord formRecord ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/forms/{form-id}/form-records/batch-create")
	@Produces("application/json")
	@RequiresScope("everything.write")
	public FormRecord postFormFormRecordBatchCreate( @PathParam("form-id") Long formId , FormRecord formRecord ) throws Exception;

}