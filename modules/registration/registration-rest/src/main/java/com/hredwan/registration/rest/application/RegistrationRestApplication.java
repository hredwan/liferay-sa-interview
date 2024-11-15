package com.hredwan.registration.rest.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import org.osgi.service.component.annotations.Component;

@ApplicationPath("/registrations-rest")
@Component(
    property = {
        "osgi.jaxrs.application.base=/registrations-rest",
        "osgi.jaxrs.name=Registration.Rest",
        "liferay.access.control.disable=true" //guest
    },
    service = Application.class
)
public class RegistrationRestApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new RegistrationResource());
        return singletons;
    }
}
