package com.fdmgroup.webclient_demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Contact {

    private long contactId;
    @NotNull(message = "First name cannot be null.")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;
    @Pattern(regexp = "\\+[0-9]{1,3} [0-9]{10}", message = "Please enter a valid phone number")
    private String phoneNumber;

    public Contact() {
    }

    public Contact(String firstName, String middleName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact [contactId=" + contactId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", middleName=" + middleName + ", phoneNumber=" + phoneNumber + "]";
    }

}
