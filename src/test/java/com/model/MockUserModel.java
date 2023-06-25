package com.model;

import org.openqa.selenium.By;

public class MockUserModel {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipInput;
    private String telephone;
    private String ssn;
    private String password;
    private String username;


    public MockUserModel(String firstName, String lastName, String address, String city, String state, String zipInput, String telephone, String ssn, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipInput = zipInput;
        this.telephone = telephone;
        this.ssn = ssn;
        this.password = password;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipInput() {
        return zipInput;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getSsn() {
        return ssn;
    }
}
