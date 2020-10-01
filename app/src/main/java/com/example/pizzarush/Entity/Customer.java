package com.example.pizzarush.Entity;

public class Customer {
    private String cid;
    private String fname;
    private String lname;
    private String mobile;
    private String email;
    private String pass;

    public Customer() {
    }

    public Customer(String cid, String fname, String lname, String mobile, String email, String pass) {
        this.cid = cid;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.email = email;
        this.pass = pass;
    }

    public Customer(String fname, String lname, String mobile, String email, String pass) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.email = email;
        this.pass = pass;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
