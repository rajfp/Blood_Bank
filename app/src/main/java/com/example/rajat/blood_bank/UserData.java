package com.example.rajat.blood_bank;

/**
 * Created by Rajat on 31-May-17.
 */
public class UserData {
    private String userName;
    private String password;
    private String address;
    private  String contact;
    private String blood_group;
    private String email;
    String name;

    public UserData() {

    }

    public UserData(String name,String userName, String password, String address,String contact,String blood_group,String email) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.contact=contact;
        this.email=email;
        this.blood_group=blood_group;
        this.name=name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getBgroup() {
        return blood_group;
    }

    public void setBgroup(String blood_group ) {
        this.blood_group = blood_group;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}

