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

package com.liferay.portlet.social.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.model.SocialActivitySetModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SocialActivitySet service. Represents a row in the &quot;SocialActivitySet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>SocialActivitySetModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SocialActivitySetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetImpl
 * @generated
 */
public class SocialActivitySetModelImpl
	extends BaseModelImpl<SocialActivitySet> implements SocialActivitySetModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a social activity set model instance should use the <code>SocialActivitySet</code> interface instead.
	 */
	public static final String TABLE_NAME = "SocialActivitySet";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"activitySetId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.BIGINT}, {"modifiedDate", Types.BIGINT},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"type_", Types.INTEGER}, {"extraData", Types.VARCHAR},
		{"activityCount", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("activitySetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("extraData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("activityCount", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SocialActivitySet (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,activitySetId LONG not null,groupId LONG,companyId LONG,userId LONG,createDate LONG,modifiedDate LONG,classNameId LONG,classPK LONG,type_ INTEGER,extraData STRING null,activityCount INTEGER,primary key (activitySetId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table SocialActivitySet";

	public static final String ORDER_BY_JPQL =
		" ORDER BY socialActivitySet.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SocialActivitySet.modifiedDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long TYPE_COLUMN_BITMASK = 8L;

	public static final long USERID_COLUMN_BITMASK = 16L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.social.kernel.model.SocialActivitySet"));

	public SocialActivitySetModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _activitySetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setActivitySetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _activitySetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SocialActivitySet.class;
	}

	@Override
	public String getModelClassName() {
		return SocialActivitySet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SocialActivitySet, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SocialActivitySet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivitySet, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SocialActivitySet)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SocialActivitySet, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SocialActivitySet, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SocialActivitySet)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SocialActivitySet, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SocialActivitySet, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SocialActivitySet>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SocialActivitySet.class.getClassLoader(), SocialActivitySet.class,
			ModelWrapper.class);

		try {
			Constructor<SocialActivitySet> constructor =
				(Constructor<SocialActivitySet>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<SocialActivitySet, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SocialActivitySet, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SocialActivitySet, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SocialActivitySet, Object>>();
		Map<String, BiConsumer<SocialActivitySet, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SocialActivitySet, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", SocialActivitySet::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", SocialActivitySet::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setCtCollectionId);
		attributeGetterFunctions.put(
			"activitySetId", SocialActivitySet::getActivitySetId);
		attributeSetterBiConsumers.put(
			"activitySetId",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setActivitySetId);
		attributeGetterFunctions.put("groupId", SocialActivitySet::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<SocialActivitySet, Long>)SocialActivitySet::setGroupId);
		attributeGetterFunctions.put(
			"companyId", SocialActivitySet::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setCompanyId);
		attributeGetterFunctions.put("userId", SocialActivitySet::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<SocialActivitySet, Long>)SocialActivitySet::setUserId);
		attributeGetterFunctions.put(
			"createDate", SocialActivitySet::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", SocialActivitySet::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", SocialActivitySet::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SocialActivitySet, Long>)
				SocialActivitySet::setClassNameId);
		attributeGetterFunctions.put("classPK", SocialActivitySet::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<SocialActivitySet, Long>)SocialActivitySet::setClassPK);
		attributeGetterFunctions.put("type", SocialActivitySet::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<SocialActivitySet, Integer>)SocialActivitySet::setType);
		attributeGetterFunctions.put(
			"extraData", SocialActivitySet::getExtraData);
		attributeSetterBiConsumers.put(
			"extraData",
			(BiConsumer<SocialActivitySet, String>)
				SocialActivitySet::setExtraData);
		attributeGetterFunctions.put(
			"activityCount", SocialActivitySet::getActivityCount);
		attributeSetterBiConsumers.put(
			"activityCount",
			(BiConsumer<SocialActivitySet, Integer>)
				SocialActivitySet::setActivityCount);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getActivitySetId() {
		return _activitySetId;
	}

	@Override
	public void setActivitySetId(long activitySetId) {
		_activitySetId = activitySetId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public long getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	@Override
	public long getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(long modifiedDate) {
		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@Override
	public String getExtraData() {
		if (_extraData == null) {
			return "";
		}
		else {
			return _extraData;
		}
	}

	@Override
	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	@Override
	public int getActivityCount() {
		return _activityCount;
	}

	@Override
	public void setActivityCount(int activityCount) {
		_activityCount = activityCount;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SocialActivitySet.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SocialActivitySet toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SocialActivitySet>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SocialActivitySetImpl socialActivitySetImpl =
			new SocialActivitySetImpl();

		socialActivitySetImpl.setMvccVersion(getMvccVersion());
		socialActivitySetImpl.setCtCollectionId(getCtCollectionId());
		socialActivitySetImpl.setActivitySetId(getActivitySetId());
		socialActivitySetImpl.setGroupId(getGroupId());
		socialActivitySetImpl.setCompanyId(getCompanyId());
		socialActivitySetImpl.setUserId(getUserId());
		socialActivitySetImpl.setCreateDate(getCreateDate());
		socialActivitySetImpl.setModifiedDate(getModifiedDate());
		socialActivitySetImpl.setClassNameId(getClassNameId());
		socialActivitySetImpl.setClassPK(getClassPK());
		socialActivitySetImpl.setType(getType());
		socialActivitySetImpl.setExtraData(getExtraData());
		socialActivitySetImpl.setActivityCount(getActivityCount());

		socialActivitySetImpl.resetOriginalValues();

		return socialActivitySetImpl;
	}

	@Override
	public int compareTo(SocialActivitySet socialActivitySet) {
		int value = 0;

		if (getModifiedDate() < socialActivitySet.getModifiedDate()) {
			value = -1;
		}
		else if (getModifiedDate() > socialActivitySet.getModifiedDate()) {
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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SocialActivitySet)) {
			return false;
		}

		SocialActivitySet socialActivitySet = (SocialActivitySet)object;

		long primaryKey = socialActivitySet.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SocialActivitySetModelImpl socialActivitySetModelImpl = this;

		socialActivitySetModelImpl._originalGroupId =
			socialActivitySetModelImpl._groupId;

		socialActivitySetModelImpl._setOriginalGroupId = false;

		socialActivitySetModelImpl._originalUserId =
			socialActivitySetModelImpl._userId;

		socialActivitySetModelImpl._setOriginalUserId = false;

		socialActivitySetModelImpl._originalClassNameId =
			socialActivitySetModelImpl._classNameId;

		socialActivitySetModelImpl._setOriginalClassNameId = false;

		socialActivitySetModelImpl._originalClassPK =
			socialActivitySetModelImpl._classPK;

		socialActivitySetModelImpl._setOriginalClassPK = false;

		socialActivitySetModelImpl._originalType =
			socialActivitySetModelImpl._type;

		socialActivitySetModelImpl._setOriginalType = false;

		socialActivitySetModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SocialActivitySet> toCacheModel() {
		SocialActivitySetCacheModel socialActivitySetCacheModel =
			new SocialActivitySetCacheModel();

		socialActivitySetCacheModel.mvccVersion = getMvccVersion();

		socialActivitySetCacheModel.ctCollectionId = getCtCollectionId();

		socialActivitySetCacheModel.activitySetId = getActivitySetId();

		socialActivitySetCacheModel.groupId = getGroupId();

		socialActivitySetCacheModel.companyId = getCompanyId();

		socialActivitySetCacheModel.userId = getUserId();

		socialActivitySetCacheModel.createDate = getCreateDate();

		socialActivitySetCacheModel.modifiedDate = getModifiedDate();

		socialActivitySetCacheModel.classNameId = getClassNameId();

		socialActivitySetCacheModel.classPK = getClassPK();

		socialActivitySetCacheModel.type = getType();

		socialActivitySetCacheModel.extraData = getExtraData();

		String extraData = socialActivitySetCacheModel.extraData;

		if ((extraData != null) && (extraData.length() == 0)) {
			socialActivitySetCacheModel.extraData = null;
		}

		socialActivitySetCacheModel.activityCount = getActivityCount();

		return socialActivitySetCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SocialActivitySet, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SocialActivitySet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivitySet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SocialActivitySet)this));
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
		Map<String, Function<SocialActivitySet, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SocialActivitySet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SocialActivitySet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SocialActivitySet)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SocialActivitySet>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _activitySetId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _createDate;
	private long _modifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private String _extraData;
	private int _activityCount;
	private long _columnBitmask;
	private SocialActivitySet _escapedModel;

}