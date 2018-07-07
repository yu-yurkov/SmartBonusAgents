package com.example.hp.smartbonusagents.model;

public class Products {

    private String photo;
    private String name;
    private String price;

    public Products() {
    }

    public Products(String photo, String name, String price) {
        this.photo = photo;
        this.name = name;
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
