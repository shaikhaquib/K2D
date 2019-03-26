package com.abatechnology.kirana2door.models;

public class User {

    private String id, email, name, pincode, contact, address, city, state, country;

    public User(String id, String email, String name, String pincode, String contact, String address, String city, String state, String country) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pincode = pincode;
        this.contact = contact;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPincode() {
        return pincode;
    }

    public String getContact() {
        return contact;
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

    public String getCountry() {
        return country;
    }
}
