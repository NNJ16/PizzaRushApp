package com.example.pizzarush.Entity;

public class Location {
    private String id;
    private String location;


    public Location(String address) {
    }

    public Location(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}