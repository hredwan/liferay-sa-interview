/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hredwan.registration.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Registration_Registration&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Registration
 * @generated
 */
public class RegistrationTable extends BaseTable<RegistrationTable> {

	public static final RegistrationTable INSTANCE = new RegistrationTable();

	public final Column<RegistrationTable, Long> registrationId = createColumn(
		"registrationId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<RegistrationTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RegistrationTable, String> surname = createColumn(
		"surname", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RegistrationTable, Date> dateOfBirth = createColumn(
		"dateOfBirth", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<RegistrationTable, String> email = createColumn(
		"email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<RegistrationTable, Date> registrationDate =
		createColumn(
			"registrationDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);

	private RegistrationTable() {
		super("Registration_Registration", RegistrationTable::new);
	}

}