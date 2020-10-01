package com.example.pizzarush.Entity;

public class Order {
    private String sdes;
    private String stotal;
    private String cid;

    public Order()
    {

    }
    public Order(String sdes, String stotal, String cid) {
        this.sdes = sdes;
        this.stotal = stotal;
        this.cid = cid;
    }

    public String getSdes() {
        return sdes;
    }

    public void setSdes(String sdes) {
        this.sdes = sdes;
    }

    public String getStotal() {
        return stotal;
    }

    public void setStotal(String stotal) {
        this.stotal = stotal;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}

