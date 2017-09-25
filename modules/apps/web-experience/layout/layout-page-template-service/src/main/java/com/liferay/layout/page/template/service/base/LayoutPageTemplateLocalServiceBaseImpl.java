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

package com.liferay.layout.page.template.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.layout.page.template.model.LayoutPageTemplate;
import com.liferay.layout.page.template.service.LayoutPageTemplateLocalService;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateFolderPersistence;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplatePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the layout page template local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.layout.page.template.service.impl.LayoutPageTemplateLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.layout.page.template.service.impl.LayoutPageTemplateLocalServiceImpl
 * @see com.liferay.layout.page.template.service.LayoutPageTemplateLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class LayoutPageTemplateLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements LayoutPageTemplateLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.layout.page.template.service.LayoutPageTemplateLocalServiceUtil} to access the layout page template local service.
	 */

	/**
	 * Adds the layout page template to the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPageTemplate the layout page template
	 * @return the layout page template that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutPageTemplate addLayoutPageTemplate(
		LayoutPageTemplate layoutPageTemplate) {
		layoutPageTemplate.setNew(true);

		return layoutPageTemplatePersistence.update(layoutPageTemplate);
	}

	/**
	 * Creates a new layout page template with the primary key. Does not add the layout page template to the database.
	 *
	 * @param layoutPageTemplateId the primary key for the new layout page template
	 * @return the new layout page template
	 */
	@Override
	public LayoutPageTemplate createLayoutPageTemplate(
		long layoutPageTemplateId) {
		return layoutPageTemplatePersistence.create(layoutPageTemplateId);
	}

	/**
	 * Deletes the layout page template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPageTemplateId the primary key of the layout page template
	 * @return the layout page template that was removed
	 * @throws PortalException if a layout page template with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutPageTemplate deleteLayoutPageTemplate(
		long layoutPageTemplateId) throws PortalException {
		return layoutPageTemplatePersistence.remove(layoutPageTemplateId);
	}

	/**
	 * Deletes the layout page template from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPageTemplate the layout page template
	 * @return the layout page template that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LayoutPageTemplate deleteLayoutPageTemplate(
		LayoutPageTemplate layoutPageTemplate) {
		return layoutPageTemplatePersistence.remove(layoutPageTemplate);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(LayoutPageTemplate.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return layoutPageTemplatePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.page.template.model.impl.LayoutPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return layoutPageTemplatePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.page.template.model.impl.LayoutPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return layoutPageTemplatePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return layoutPageTemplatePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return layoutPageTemplatePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public LayoutPageTemplate fetchLayoutPageTemplate(long layoutPageTemplateId) {
		return layoutPageTemplatePersistence.fetchByPrimaryKey(layoutPageTemplateId);
	}

	/**
	 * Returns the layout page template with the primary key.
	 *
	 * @param layoutPageTemplateId the primary key of the layout page template
	 * @return the layout page template
	 * @throws PortalException if a layout page template with the primary key could not be found
	 */
	@Override
	public LayoutPageTemplate getLayoutPageTemplate(long layoutPageTemplateId)
		throws PortalException {
		return layoutPageTemplatePersistence.findByPrimaryKey(layoutPageTemplateId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(layoutPageTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutPageTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("layoutPageTemplateId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(layoutPageTemplateLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(LayoutPageTemplate.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"layoutPageTemplateId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(layoutPageTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(LayoutPageTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("layoutPageTemplateId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return layoutPageTemplateLocalService.deleteLayoutPageTemplate((LayoutPageTemplate)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return layoutPageTemplatePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the layout page templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.layout.page.template.model.impl.LayoutPageTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout page templates
	 * @param end the upper bound of the range of layout page templates (not inclusive)
	 * @return the range of layout page templates
	 */
	@Override
	public List<LayoutPageTemplate> getLayoutPageTemplates(int start, int end) {
		return layoutPageTemplatePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of layout page templates.
	 *
	 * @return the number of layout page templates
	 */
	@Override
	public int getLayoutPageTemplatesCount() {
		return layoutPageTemplatePersistence.countAll();
	}

	/**
	 * Updates the layout page template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPageTemplate the layout page template
	 * @return the layout page template that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LayoutPageTemplate updateLayoutPageTemplate(
		LayoutPageTemplate layoutPageTemplate) {
		return layoutPageTemplatePersistence.update(layoutPageTemplate);
	}

	/**
	 * Returns the layout page template local service.
	 *
	 * @return the layout page template local service
	 */
	public LayoutPageTemplateLocalService getLayoutPageTemplateLocalService() {
		return layoutPageTemplateLocalService;
	}

	/**
	 * Sets the layout page template local service.
	 *
	 * @param layoutPageTemplateLocalService the layout page template local service
	 */
	public void setLayoutPageTemplateLocalService(
		LayoutPageTemplateLocalService layoutPageTemplateLocalService) {
		this.layoutPageTemplateLocalService = layoutPageTemplateLocalService;
	}

	/**
	 * Returns the layout page template persistence.
	 *
	 * @return the layout page template persistence
	 */
	public LayoutPageTemplatePersistence getLayoutPageTemplatePersistence() {
		return layoutPageTemplatePersistence;
	}

	/**
	 * Sets the layout page template persistence.
	 *
	 * @param layoutPageTemplatePersistence the layout page template persistence
	 */
	public void setLayoutPageTemplatePersistence(
		LayoutPageTemplatePersistence layoutPageTemplatePersistence) {
		this.layoutPageTemplatePersistence = layoutPageTemplatePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the layout page template folder local service.
	 *
	 * @return the layout page template folder local service
	 */
	public com.liferay.layout.page.template.service.LayoutPageTemplateFolderLocalService getLayoutPageTemplateFolderLocalService() {
		return layoutPageTemplateFolderLocalService;
	}

	/**
	 * Sets the layout page template folder local service.
	 *
	 * @param layoutPageTemplateFolderLocalService the layout page template folder local service
	 */
	public void setLayoutPageTemplateFolderLocalService(
		com.liferay.layout.page.template.service.LayoutPageTemplateFolderLocalService layoutPageTemplateFolderLocalService) {
		this.layoutPageTemplateFolderLocalService = layoutPageTemplateFolderLocalService;
	}

	/**
	 * Returns the layout page template folder persistence.
	 *
	 * @return the layout page template folder persistence
	 */
	public LayoutPageTemplateFolderPersistence getLayoutPageTemplateFolderPersistence() {
		return layoutPageTemplateFolderPersistence;
	}

	/**
	 * Sets the layout page template folder persistence.
	 *
	 * @param layoutPageTemplateFolderPersistence the layout page template folder persistence
	 */
	public void setLayoutPageTemplateFolderPersistence(
		LayoutPageTemplateFolderPersistence layoutPageTemplateFolderPersistence) {
		this.layoutPageTemplateFolderPersistence = layoutPageTemplateFolderPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.layout.page.template.model.LayoutPageTemplate",
			layoutPageTemplateLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.layout.page.template.model.LayoutPageTemplate");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutPageTemplateLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return LayoutPageTemplate.class;
	}

	protected String getModelClassName() {
		return LayoutPageTemplate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutPageTemplatePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = LayoutPageTemplateLocalService.class)
	protected LayoutPageTemplateLocalService layoutPageTemplateLocalService;
	@BeanReference(type = LayoutPageTemplatePersistence.class)
	protected LayoutPageTemplatePersistence layoutPageTemplatePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = com.liferay.layout.page.template.service.LayoutPageTemplateFolderLocalService.class)
	protected com.liferay.layout.page.template.service.LayoutPageTemplateFolderLocalService layoutPageTemplateFolderLocalService;
	@BeanReference(type = LayoutPageTemplateFolderPersistence.class)
	protected LayoutPageTemplateFolderPersistence layoutPageTemplateFolderPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}