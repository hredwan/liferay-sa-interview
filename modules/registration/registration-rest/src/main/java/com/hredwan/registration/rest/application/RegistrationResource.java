package com.hredwan.registration.rest.application;


import com.hredwan.registration.model.Registration;
import com.hredwan.registration.service.RegistrationLocalService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

@Component(
    service = RegistrationResource.class,
    property = {
        "osgi.jaxrs.resource=true", // Add this property to ensure it is registered as a JAX-RS resource
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=Registration.Rest)" // Ensure this matches your application name
    }
)
@Path("/registrations")
public class RegistrationResource {

    private static final Log _log = LogFactoryUtil.getLog(RegistrationResource.class);

    private static final String PROJECT_ID = "PROJECT_ID";
    private static final String RECAPTCHA_SITE_KEY = "RECAPTCHA_SITE_KEY";
    private static final String RECAPTCHA_ACTION = "USER_ACTION";
    private static final String RECAPTCHA_SECRET_KEY = "RECAPTCHA_SECRET_KEY";

    @Reference
    private RegistrationLocalService _registrationLocalService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRegistration(RegistrationDTO registration) {
        try {
            _log.info("Registration Started!");
            // Verify reCAPTCHA

            boolean isRecaptchaValid = this.verifyRecaptcha(registration.getRecaptcha());

            if (!isRecaptchaValid) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid reCAPTCHA response.")
                        .build();
            }
            if (
                    registration.getName() == null ||
                    registration.getSurname() == null ||
                    registration.getDateOfBirth() == null ||
                    registration.getEmail() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("All fields are required").build();
            }
            Registration newRegistration = _registrationLocalService.createRegistration(0);
            newRegistration.setName(registration.getName());
            newRegistration.setSurname(registration.getSurname());
            newRegistration.setDateOfBirth(registration.getDateOfBirth());
            newRegistration.setEmail(registration.getEmail());
            newRegistration.setRegistrationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

            // Save the registration using the service
            _registrationLocalService.addRegistration(newRegistration);

            _log.info("Registration successfully added!");

            return Response.status(Response.Status.CREATED).entity(newRegistration.getModelAttributes()).build();
        }
        catch (RuntimeException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Email address already in use.")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while processing the request.")
                    .build();
        }
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response testMethod() {
        return Response.ok("It works!").build();
    }

   public boolean verifyRecaptcha(String token) throws IOException {
       String requestBody = String.format(
               "{ \"event\": { \"token\": \"%s\",  \"siteKey\": \"%s\" } }",
               token,  RECAPTCHA_SITE_KEY
       );

       try {
           URL url = new URL("https://recaptchaenterprise.googleapis.com/v1/projects/model-axe-137723/assessments?key=" + RECAPTCHA_SECRET_KEY);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("POST");
           conn.setRequestProperty("Content-Type", "application/json");
           conn.setDoOutput(true);

           // Write the request body
           try (OutputStream os = conn.getOutputStream()) {
               os.write(requestBody.getBytes());
               os.flush();
           }

           // Read the response
           Scanner scanner = new Scanner(conn.getInputStream(), "UTF-8");
           String response = scanner.useDelimiter("\\A").next();
           scanner.close();
           _log.info(response);


           JSONObject jsonResponse = new JSONObject(response);
           double score = jsonResponse.getJSONObject("riskAnalysis").getDouble("score");



           return score >= 0.5;
       } catch (Exception e) {
           _log.error(e.getMessage());
           return false;
       }
    }

}
