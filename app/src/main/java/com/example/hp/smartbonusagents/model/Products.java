package com.example.hp.smartbonusagents.model;

public class Products {

    private int id;
    private String photo;
    private String name;
    private float price;

    public Products() {
    }

    public Products(int id, String photo, String name, float price) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
