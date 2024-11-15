/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hredwan.registration.model.impl;

import com.hredwan.registration.model.Registration;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Registration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RegistrationCacheModel
	implements CacheModel<Registration>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RegistrationCacheModel)) {
			return false;
		}

		RegistrationCacheModel registrationCacheModel =
			(RegistrationCacheModel)object;

		if (registrationId == registrationCacheModel.registrationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, registrationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{registrationId=");
		sb.append(registrationId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", surname=");
		sb.append(surname);
		sb.append(", dateOfBirth=");
		sb.append(dateOfBirth);
		sb.append(", email=");
		sb.append(email);
		sb.append(", registrationDate=");
		sb.append(registrationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Registration toEntityModel() {
		RegistrationImpl registrationImpl = new RegistrationImpl();

		registrationImpl.setRegistrationId(registrationId);

		if (name == null) {
			registrationImpl.setName("");
		}
		else {
			registrationImpl.setName(name);
		}

		if (surname == null) {
			registrationImpl.setSurname("");
		}
		else {
			registrationImpl.setSurname(surname);
		}

		if (dateOfBirth == Long.MIN_VALUE) {
			registrationImpl.setDateOfBirth(null);
		}
		else {
			registrationImpl.setDateOfBirth(new Date(dateOfBirth));
		}

		if (email == null) {
			registrationImpl.setEmail("");
		}
		else {
			registrationImpl.setEmail(email);
		}

		if (registrationDate == Long.MIN_VALUE) {
			registrationImpl.setRegistrationDate(null);
		}
		else {
			registrationImpl.setRegistrationDate(new Date(registrationDate));
		}

		registrationImpl.resetOriginalValues();

		return registrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		registrationId = objectInput.readLong();
		name = objectInput.readUTF();
		surname = objectInput.readUTF();
		dateOfBirth = objectInput.readLong();
		email = objectInput.readUTF();
		registrationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(registrationId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (surname == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(surname);
		}

		objectOutput.writeLong(dateOfBirth);

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeLong(registrationDate);
	}

	public long registrationId;
	public String name;
	public String surname;
	public long dateOfBirth;
	public String email;
	public long registrationDate;

}