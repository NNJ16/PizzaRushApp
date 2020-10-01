package com.example.pizzarush.Entity;

public class Assign {

    private String orderID;
    private String riderID;

    public Assign() {
    }

    public Assign(String orderID, String riderID) {
        this.orderID = orderID;
        this.riderID = riderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getRiderID() {
        return riderID;
    }

    public void setRiderID(String riderID) {
        this.riderID = riderID;
    }
}
