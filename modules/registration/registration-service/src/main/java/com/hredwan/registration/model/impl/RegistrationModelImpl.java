/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hredwan.registration.model.impl;

import com.hredwan.registration.model.Registration;
import com.hredwan.registration.model.RegistrationModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Registration service. Represents a row in the &quot;Registration_Registration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RegistrationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RegistrationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegistrationImpl
 * @generated
 */
public class RegistrationModelImpl
	extends BaseModelImpl<Registration> implements RegistrationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a registration model instance should use the <code>Registration</code> interface instead.
	 */
	public static final String TABLE_NAME = "Registration_Registration";

	public static final Object[][] TABLE_COLUMNS = {
		{"registrationId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"surname", Types.VARCHAR}, {"dateOfBirth", Types.TIMESTAMP},
		{"email", Types.VARCHAR}, {"registrationDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("registrationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("surname", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dateOfBirth", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("registrationDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Registration_Registration (registrationId LONG not null primary key,name VARCHAR(75) null,surname VARCHAR(75) null,dateOfBirth DATE null,email VARCHAR(75) null,registrationDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table Registration_Registration";

	public static final String ORDER_BY_JPQL =
		" ORDER BY registration.registrationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Registration_Registration.registrationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EMAIL_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long REGISTRATIONID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public RegistrationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _registrationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRegistrationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _registrationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Registration.class;
	}

	@Override
	public String getModelClassName() {
		return Registration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Registration, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Registration, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Registration, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Registration)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Registration, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Registration, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Registration)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Registration, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Registration, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<Registration, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<Registration, Object>>
				attributeGetterFunctions =
					new LinkedHashMap<String, Function<Registration, Object>>();

			attributeGetterFunctions.put(
				"registrationId", Registration::getRegistrationId);
			attributeGetterFunctions.put("name", Registration::getName);
			attributeGetterFunctions.put("surname", Registration::getSurname);
			attributeGetterFunctions.put(
				"dateOfBirth", Registration::getDateOfBirth);
			attributeGetterFunctions.put("email", Registration::getEmail);
			attributeGetterFunctions.put(
				"registrationDate", Registration::getRegistrationDate);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<Registration, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<Registration, ?>>
				attributeSetterBiConsumers =
					new LinkedHashMap<String, BiConsumer<Registration, ?>>();

			attributeSetterBiConsumers.put(
				"registrationId",
				(BiConsumer<Registration, Long>)
					Registration::setRegistrationId);
			attributeSetterBiConsumers.put(
				"name",
				(BiConsumer<Registration, String>)Registration::setName);
			attributeSetterBiConsumers.put(
				"surname",
				(BiConsumer<Registration, String>)Registration::setSurname);
			attributeSetterBiConsumers.put(
				"dateOfBirth",
				(BiConsumer<Registration, Date>)Registration::setDateOfBirth);
			attributeSetterBiConsumers.put(
				"email",
				(BiConsumer<Registration, String>)Registration::setEmail);
			attributeSetterBiConsumers.put(
				"registrationDate",
				(BiConsumer<Registration, Date>)
					Registration::setRegistrationDate);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public long getRegistrationId() {
		return _registrationId;
	}

	@Override
	public void setRegistrationId(long registrationId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_registrationId = registrationId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@Override
	public String getSurname() {
		if (_surname == null) {
			return "";
		}
		else {
			return _surname;
		}
	}

	@Override
	public void setSurname(String surname) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_surname = surname;
	}

	@Override
	public Date getDateOfBirth() {
		return _dateOfBirth;
	}

	@Override
	public void setDateOfBirth(Date dateOfBirth) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_dateOfBirth = dateOfBirth;
	}

	@Override
	public String getEmail() {
		if (_email == null) {
			return "";
		}
		else {
			return _email;
		}
	}

	@Override
	public void setEmail(String email) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_email = email;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalEmail() {
		return getColumnOriginalValue("email");
	}

	@Override
	public Date getRegistrationDate() {
		return _registrationDate;
	}

	@Override
	public void setRegistrationDate(Date registrationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_registrationDate = registrationDate;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Registration.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Registration toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Registration>
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
		RegistrationImpl registrationImpl = new RegistrationImpl();

		registrationImpl.setRegistrationId(getRegistrationId());
		registrationImpl.setName(getName());
		registrationImpl.setSurname(getSurname());
		registrationImpl.setDateOfBirth(getDateOfBirth());
		registrationImpl.setEmail(getEmail());
		registrationImpl.setRegistrationDate(getRegistrationDate());

		registrationImpl.resetOriginalValues();

		return registrationImpl;
	}

	@Override
	public Registration cloneWithOriginalValues() {
		RegistrationImpl registrationImpl = new RegistrationImpl();

		registrationImpl.setRegistrationId(
			this.<Long>getColumnOriginalValue("registrationId"));
		registrationImpl.setName(this.<String>getColumnOriginalValue("name"));
		registrationImpl.setSurname(
			this.<String>getColumnOriginalValue("surname"));
		registrationImpl.setDateOfBirth(
			this.<Date>getColumnOriginalValue("dateOfBirth"));
		registrationImpl.setEmail(this.<String>getColumnOriginalValue("email"));
		registrationImpl.setRegistrationDate(
			this.<Date>getColumnOriginalValue("registrationDate"));

		return registrationImpl;
	}

	@Override
	public int compareTo(Registration registration) {
		long primaryKey = registration.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Registration)) {
			return false;
		}

		Registration registration = (Registration)object;

		long primaryKey = registration.getPrimaryKey();

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
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Registration> toCacheModel() {
		RegistrationCacheModel registrationCacheModel =
			new RegistrationCacheModel();

		registrationCacheModel.registrationId = getRegistrationId();

		registrationCacheModel.name = getName();

		String name = registrationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			registrationCacheModel.name = null;
		}

		registrationCacheModel.surname = getSurname();

		String surname = registrationCacheModel.surname;

		if ((surname != null) && (surname.length() == 0)) {
			registrationCacheModel.surname = null;
		}

		Date dateOfBirth = getDateOfBirth();

		if (dateOfBirth != null) {
			registrationCacheModel.dateOfBirth = dateOfBirth.getTime();
		}
		else {
			registrationCacheModel.dateOfBirth = Long.MIN_VALUE;
		}

		registrationCacheModel.email = getEmail();

		String email = registrationCacheModel.email;

		if ((email != null) && (email.length() == 0)) {
			registrationCacheModel.email = null;
		}

		Date registrationDate = getRegistrationDate();

		if (registrationDate != null) {
			registrationCacheModel.registrationDate =
				registrationDate.getTime();
		}
		else {
			registrationCacheModel.registrationDate = Long.MIN_VALUE;
		}

		return registrationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Registration, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Registration, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Registration, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Registration)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Registration>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Registration.class, ModelWrapper.class);

	}

	private long _registrationId;
	private String _name;
	private String _surname;
	private Date _dateOfBirth;
	private String _email;
	private Date _registrationDate;

	public <T> T getColumnValue(String columnName) {
		Function<Registration, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Registration)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("registrationId", _registrationId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("surname", _surname);
		_columnOriginalValues.put("dateOfBirth", _dateOfBirth);
		_columnOriginalValues.put("email", _email);
		_columnOriginalValues.put("registrationDate", _registrationDate);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("registrationId", 1L);

		columnBitmasks.put("name", 2L);

		columnBitmasks.put("surname", 4L);

		columnBitmasks.put("dateOfBirth", 8L);

		columnBitmasks.put("email", 16L);

		columnBitmasks.put("registrationDate", 32L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Registration _escapedModel;

}