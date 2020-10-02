package com.example.pizzarush.Entity;

public class Item {
    String id;
    String name;
    String ingre;
    String description;
    String type;
    int price;


//    public String getImgid() {
//        return imgid;
//    }
//
//    public void setImgid(String imgid) {
//        this.imgid = imgid;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngre() {
        return ingre;
    }

    public void setIngre(String ingre) {
        this.ingre = ingre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item(String id, String name, String ingre, String description, String type, int price) {
        this.id = id;
        this.name = name;
        this.ingre = ingre;
        this.description = description;
        this.type = type;
        this.price = price;
    }
}
