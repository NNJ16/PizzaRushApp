package com.example.pizzarush.Entity;

public class Point {
    private String cid;
    private String mLevel;
    private int points;

    public Point(String cid, String mLevel, int points) {
        this.cid = cid;
        this.mLevel = mLevel;
        this.points = points;
    }

    public Point(String mLevel, int points) {
        this.mLevel = mLevel;
        this.points = points;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
