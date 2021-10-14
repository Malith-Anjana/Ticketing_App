package com.example.csse_project;

public class UserHelper {
    String name, tokenid, email, phoneNo, password;
    float balance;

    public UserHelper() {
    }

    public UserHelper(String name,String tokenid,  String email, String phoneNo, String password) {

        this.name = name;
        this.tokenid = tokenid;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public UserHelper(String name, String tokenid, String email, String phoneNo, float balance) {
        this.name = name;
        this.tokenid = tokenid;
        this.email = email;
        this.phoneNo = phoneNo;
        this.balance = balance;
    }

    public UserHelper(float balance) {
        this.balance = balance;
    }

    public UserHelper(String name) {
        this.name = name;
    }



    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
