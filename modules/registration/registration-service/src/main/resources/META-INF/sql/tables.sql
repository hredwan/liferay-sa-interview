create table Registration_Registration (
	registrationId LONG not null primary key,
	name VARCHAR(75) null,
	surname VARCHAR(75) null,
	dateOfBirth DATE null,
	email VARCHAR(75) null,
	registrationDate DATE null
);