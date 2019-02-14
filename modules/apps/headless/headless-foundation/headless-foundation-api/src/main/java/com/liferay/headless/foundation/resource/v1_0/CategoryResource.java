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

package com.liferay.headless.foundation.resource.v1_0;

import com.liferay.headless.foundation.dto.v1_0.Category;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-foundation/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@Path("/v1.0")
public interface CategoryResource {

	@DELETE
	@Path("/categories/{category-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Response deleteCategory( @PathParam("category-id") Long categoryId ) throws Exception;

	@GET
	@Path("/categories/{category-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Category getCategory( @PathParam("category-id") Long categoryId ) throws Exception;

	@Consumes("application/json")
	@PUT
	@Path("/categories/{category-id}")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Category putCategory( @PathParam("category-id") Long categoryId , Category category ) throws Exception;

	@GET
	@Path("/categories/{category-id}/categories")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Page<Category> getCategoryCategoriesPage( @PathParam("category-id") Long categoryId , @Context Pagination pagination ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/categories/{category-id}/categories")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Category postCategoryCategory( @PathParam("category-id") Long categoryId , Category category ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/categories/{category-id}/categories/batch-create")
	@Produces("application/json")
	@RequiresScope("everything.write")
	public Category postCategoryCategoryBatchCreate( @PathParam("category-id") Long categoryId , Category category ) throws Exception;

	@GET
	@Path("/vocabularies/{vocabulary-id}/categories")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Page<Category> getVocabularyCategoriesPage( @PathParam("vocabulary-id") Long vocabularyId , @Context Pagination pagination ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/vocabularies/{vocabulary-id}/categories")
	@Produces("application/json")
	@RequiresScope("everything.read")
	public Category postVocabularyCategory( @PathParam("vocabulary-id") Long vocabularyId , Category category ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/vocabularies/{vocabulary-id}/categories/batch-create")
	@Produces("application/json")
	@RequiresScope("everything.write")
	public Category postVocabularyCategoryBatchCreate( @PathParam("vocabulary-id") Long vocabularyId , Category category ) throws Exception;

}