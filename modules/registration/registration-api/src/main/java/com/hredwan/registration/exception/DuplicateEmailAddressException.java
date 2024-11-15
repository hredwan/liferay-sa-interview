package com.hredwan.registration.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class DuplicateEmailAddressException extends PortalException {

    public DuplicateEmailAddressException() {
        super();
    }

    public DuplicateEmailAddressException(String msg) {
        super(msg);
    }

    public DuplicateEmailAddressException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DuplicateEmailAddressException(Throwable cause) {
        super(cause);
    }
}
