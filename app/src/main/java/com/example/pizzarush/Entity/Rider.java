package com.example.pizzarush.Entity;

public class Rider {
    private String riderId;
    private String nameR;
    private int phoneR;
    private String emailR;
    private String bikeNo;
    private int deliveredOR;


    public Rider() {
    }

    public Rider(String riderId, String nameR, int phoneR, String emailR, String bikeNo) {
        this.riderId = riderId;
        this.nameR = nameR;
        this.phoneR = phoneR;
        this.emailR = emailR;
        this.bikeNo = bikeNo;
    }

    public Rider(String riderId, String nameR, int phoneR, String emailR, String bikeNo, int deliveredOR) {
        this.riderId = riderId;
        this.nameR = nameR;
        this.phoneR = phoneR;
        this.emailR = emailR;
        this.bikeNo=bikeNo;
        this.deliveredOR = deliveredOR;
    }


    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getNameR() {
        return nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }

    public int getPhoneR() {
        return phoneR;
    }

    public void setPhoneR(int phoneR) {
        this.phoneR = phoneR;
    }

    public String getEmailR() {
        return emailR;
    }

    public void setEmailR(String emailR) {
        this.emailR = emailR;
    }

    public String getBikeNo() {
        return bikeNo;
    }

    public void setBikeNo(String bikeNo) {
        this.bikeNo = bikeNo;
    }

    public int getDeliveredOR() {
        return deliveredOR;
    }

    public void setDeliveredOR(int deliveredOR) {
        this.deliveredOR = 0;
    }

}
