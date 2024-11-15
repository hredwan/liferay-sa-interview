package com.hredwan.registration.rest.application;

import java.util.Date;

/**
 * A simple Data Transfer Object for Registration.
 */
public class RegistrationDTO {

    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String recaptcha;

    // Getters and setters
    public String getRecaptcha() {
        return recaptcha;
    }

    public void setRecaptcha(String recaptcha) {
        this.recaptcha = recaptcha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
