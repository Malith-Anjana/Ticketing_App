package com.example.csse_project;

public class CreditCardHelper {

     String tokenid,cardNumber,cvc,postalCode,mobile,expireDate,date;

    public CreditCardHelper(String cardNumber, String expireDate, String cvc, String postalCode, String mobile) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvc = cvc;
        this.postalCode = postalCode;
        this.mobile = mobile;
    }

    public CreditCardHelper(String tokenid, String cardNumber, String cvc, String postalCode, String mobile, String expireDate, String date) {
        this.tokenid = tokenid;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.postalCode = postalCode;
        this.mobile = mobile;
        this.expireDate = expireDate;
        this.date = date;
    }

    public CreditCardHelper(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
