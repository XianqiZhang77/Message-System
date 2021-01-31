package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

public class Person {

    int age;
    String gender;
    @NonNull
    Address address;

    public Person() {
    }

    public Person(int age, String gender, Address address) {
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    @JsonIgnore
    public String getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public String getGreeting() {
        return "Hello";
    }
}

class Address {
    String city;
    String province;
    String street;
    String postalCode;

    public Address() {
    }

    public Address(String city, String province, String street, String postalCode) {
        this.city = city;
        this.province = province;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
