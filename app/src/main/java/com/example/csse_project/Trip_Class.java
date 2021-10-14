package com.example.csse_project;

public class Trip_Class {
    private String tokenID;
    private String start;
    private String end;
    private String distance;
    private String qty;
    private String fee;
    private String date;

    public Trip_Class() {
    }

    public Trip_Class(String tokenID, String start, String end, String distance, String qty, String fee, String date) {
        this.tokenID = tokenID;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.qty = qty;
        this.fee = fee;
        this.date = date;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
