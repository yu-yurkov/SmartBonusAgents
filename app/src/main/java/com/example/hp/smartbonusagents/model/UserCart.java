package com.example.hp.smartbonusagents.model;

import java.util.ArrayList;

public class UserCart {

    private int id;
    private String name;
    private Float price;
    private String photo;
    private int quantity;

    public UserCart(){

    }

    public UserCart(int id, String name, Float price, String photo, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "UserCart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
