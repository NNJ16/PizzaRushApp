package com.example.pizzarush.Entity;

public class OrderItem {

    private double pizza_size_price=0;
    private String pizza_des;
    private double beverages_size_price=0;
    private String beverages_des;
    private double meat_price=0;
    private double cheese_price=0;
    private double BBQ_price=0;
    private String sid;
    private String sdes;
    private String stotal;
    private String cid;

    public OrderItem(String sdes, String stotal, String cid) {

    }

    public OrderItem(double pizza_size_price, String pizza_des, double beverages_size_price, String beverages_des, double meat_price, double cheese_price, double BBQ_price, String sid, String sdes, String stotal, String cid) {
        this.pizza_size_price = pizza_size_price;
        this.pizza_des = pizza_des;
        this.beverages_size_price = beverages_size_price;
        this.beverages_des = beverages_des;
        this.meat_price = meat_price;
        this.cheese_price = cheese_price;
        this.BBQ_price = BBQ_price;
        this.sid = sid;
        this.sdes = sdes;
        this.stotal = stotal;
        this.cid = cid;
    }

    public OrderItem() {

    }

    public double getPizza_size_price() {
        return pizza_size_price;
    }

    public void setPizza_size_price(double pizza_size_price) {
        this.pizza_size_price = pizza_size_price;
    }

    public String getPizza_des() {
        return pizza_des;
    }

    public void setPizza_des(String pizza_des) {
        this.pizza_des = pizza_des;
    }

    public double getBeverages_size_price() {
        return beverages_size_price;
    }

    public void setBeverages_size_price(double beverages_size_price) {
        this.beverages_size_price = beverages_size_price;
    }

    public String getBeverages_des() {
        return beverages_des;
    }

    public void setBeverages_des(String beverages_des) {
        this.beverages_des = beverages_des;
    }

    public double getMeat_price() {
        return meat_price;
    }

    public void setMeat_price(double meat_price) {
        this.meat_price = meat_price;
    }

    public double getCheese_price() {
        return cheese_price;
    }

    public void setCheese_price(double cheese_price) {
        this.cheese_price = cheese_price;
    }

    public double getBBQ_price() {
        return BBQ_price;
    }

    public void setBBQ_price(double BBQ_price) {
        this.BBQ_price = BBQ_price;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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