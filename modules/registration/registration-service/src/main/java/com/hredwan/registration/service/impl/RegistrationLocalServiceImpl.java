/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.hredwan.registration.service.impl;

import com.hredwan.registration.exception.DuplicateEmailAddressException;
import com.hredwan.registration.exception.NoSuchRegistrationException;
import com.hredwan.registration.model.Registration;
import com.hredwan.registration.service.base.RegistrationLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.hredwan.registration.model.Registration",
	service = AopService.class
)
public class RegistrationLocalServiceImpl
	extends RegistrationLocalServiceBaseImpl {

    @Override
	public Registration addRegistration(Registration registration)  {

		// Check if the email already exists
		if (Validator.isNotNull(registration.getEmail()) && hasRegistrationWithEmail(registration.getEmail())) {
			throw new RuntimeException("The email address is already in use.");
		}

		return super.addRegistration(registration);
	}

	private boolean hasRegistrationWithEmail(String email) {
        try {
            return registrationPersistence.findByEmail(email) != null;
        } catch (NoSuchRegistrationException e) {
            return false;
        }
    }
}