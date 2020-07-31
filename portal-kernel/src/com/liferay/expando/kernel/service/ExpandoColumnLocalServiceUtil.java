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

package com.liferay.expando.kernel.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * Provides the local service utility for ExpandoColumn. This utility wraps
 * <code>com.liferay.portlet.expando.service.impl.ExpandoColumnLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoColumnLocalService
 * @generated
 */
public class ExpandoColumnLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portlet.expando.service.impl.ExpandoColumnLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn addColumn(
			long tableId, String name, int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addColumn(tableId, name, type);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn addColumn(
			long tableId, String name, int type, Object defaultData)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addColumn(tableId, name, type, defaultData);
	}

	/**
	 * Adds the expando column to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpandoColumnLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param expandoColumn the expando column
	 * @return the expando column that was added
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn
		addExpandoColumn(
			com.liferay.expando.kernel.model.ExpandoColumn expandoColumn) {

		return getService().addExpandoColumn(expandoColumn);
	}

	/**
	 * Creates a new expando column with the primary key. Does not add the expando column to the database.
	 *
	 * @param columnId the primary key for the new expando column
	 * @return the new expando column
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn
		createExpandoColumn(long columnId) {

		return getService().createExpandoColumn(columnId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	public static void deleteColumn(
		com.liferay.expando.kernel.model.ExpandoColumn column) {

		getService().deleteColumn(column);
	}

	public static void deleteColumn(long columnId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteColumn(columnId);
	}

	public static void deleteColumn(
			long companyId, long classNameId, String tableName, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteColumn(companyId, classNameId, tableName, name);
	}

	public static void deleteColumn(long tableId, String name) {
		getService().deleteColumn(tableId, name);
	}

	public static void deleteColumn(
			long companyId, String className, String tableName, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteColumn(companyId, className, tableName, name);
	}

	public static void deleteColumns(long tableId) {
		getService().deleteColumns(tableId);
	}

	public static void deleteColumns(
			long companyId, long classNameId, String tableName)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteColumns(companyId, classNameId, tableName);
	}

	public static void deleteColumns(
			long companyId, String className, String tableName)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteColumns(companyId, className, tableName);
	}

	/**
	 * Deletes the expando column from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpandoColumnLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param expandoColumn the expando column
	 * @return the expando column that was removed
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn
		deleteExpandoColumn(
			com.liferay.expando.kernel.model.ExpandoColumn expandoColumn) {

		return getService().deleteExpandoColumn(expandoColumn);
	}

	/**
	 * Deletes the expando column with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpandoColumnLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param columnId the primary key of the expando column
	 * @return the expando column that was removed
	 * @throws PortalException if a expando column with the primary key could not be found
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn
			deleteExpandoColumn(long columnId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteExpandoColumn(columnId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return getService().dslQuery(dslQuery);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.expando.model.impl.ExpandoColumnModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.expando.model.impl.ExpandoColumnModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn
		fetchExpandoColumn(long columnId) {

		return getService().fetchExpandoColumn(columnId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn getColumn(
			long columnId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getColumn(columnId);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn getColumn(
		long companyId, long classNameId, String tableName, String name) {

		return getService().getColumn(companyId, classNameId, tableName, name);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn getColumn(
		long tableId, String name) {

		return getService().getColumn(tableId, name);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn getColumn(
		long companyId, String className, String tableName, String name) {

		return getService().getColumn(companyId, className, tableName, name);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getColumns(long tableId) {

		return getService().getColumns(tableId);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getColumns(long tableId, java.util.Collection<String> names) {

		return getService().getColumns(tableId, names);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getColumns(long companyId, long classNameId, String tableName) {

		return getService().getColumns(companyId, classNameId, tableName);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getColumns(
			long companyId, long classNameId, String tableName,
			java.util.Collection<String> names) {

		return getService().getColumns(
			companyId, classNameId, tableName, names);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getColumns(long companyId, String className, String tableName) {

		return getService().getColumns(companyId, className, tableName);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getColumns(
			long companyId, String className, String tableName,
			java.util.Collection<String> columnNames) {

		return getService().getColumns(
			companyId, className, tableName, columnNames);
	}

	public static int getColumnsCount(long tableId) {
		return getService().getColumnsCount(tableId);
	}

	public static int getColumnsCount(
		long companyId, long classNameId, String tableName) {

		return getService().getColumnsCount(companyId, classNameId, tableName);
	}

	public static int getColumnsCount(
		long companyId, String className, String tableName) {

		return getService().getColumnsCount(companyId, className, tableName);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn
		getDefaultTableColumn(long companyId, long classNameId, String name) {

		return getService().getDefaultTableColumn(companyId, classNameId, name);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn
		getDefaultTableColumn(long companyId, String className, String name) {

		return getService().getDefaultTableColumn(companyId, className, name);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getDefaultTableColumns(long companyId, long classNameId) {

		return getService().getDefaultTableColumns(companyId, classNameId);
	}

	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getDefaultTableColumns(long companyId, String className) {

		return getService().getDefaultTableColumns(companyId, className);
	}

	public static int getDefaultTableColumnsCount(
		long companyId, long classNameId) {

		return getService().getDefaultTableColumnsCount(companyId, classNameId);
	}

	public static int getDefaultTableColumnsCount(
		long companyId, String className) {

		return getService().getDefaultTableColumnsCount(companyId, className);
	}

	/**
	 * Returns the expando column with the primary key.
	 *
	 * @param columnId the primary key of the expando column
	 * @return the expando column
	 * @throws PortalException if a expando column with the primary key could not be found
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn
			getExpandoColumn(long columnId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getExpandoColumn(columnId);
	}

	/**
	 * Returns a range of all the expando columns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.expando.model.impl.ExpandoColumnModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of expando columns
	 * @param end the upper bound of the range of expando columns (not inclusive)
	 * @return the range of expando columns
	 */
	public static java.util.List<com.liferay.expando.kernel.model.ExpandoColumn>
		getExpandoColumns(int start, int end) {

		return getService().getExpandoColumns(start, end);
	}

	/**
	 * Returns the number of expando columns.
	 *
	 * @return the number of expando columns
	 */
	public static int getExpandoColumnsCount() {
		return getService().getExpandoColumnsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn updateColumn(
			long columnId, String name, int type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateColumn(columnId, name, type);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn updateColumn(
			long columnId, String name, int type, Object defaultData)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateColumn(columnId, name, type, defaultData);
	}

	/**
	 * Updates the expando column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExpandoColumnLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param expandoColumn the expando column
	 * @return the expando column that was updated
	 */
	public static com.liferay.expando.kernel.model.ExpandoColumn
		updateExpandoColumn(
			com.liferay.expando.kernel.model.ExpandoColumn expandoColumn) {

		return getService().updateExpandoColumn(expandoColumn);
	}

	public static com.liferay.expando.kernel.model.ExpandoColumn
			updateTypeSettings(long columnId, String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateTypeSettings(columnId, typeSettings);
	}

	public static ExpandoColumnLocalService getService() {
		if (_service == null) {
			_service = (ExpandoColumnLocalService)PortalBeanLocatorUtil.locate(
				ExpandoColumnLocalService.class.getName());
		}

		return _service;
	}

	private static ExpandoColumnLocalService _service;

}