package com.example.hp.smartbonusagents.model;

import java.util.ArrayList;

public class UserCart {

    private int id;
    private int quantity;
    private Products p;

    public UserCart(){

    }

    public UserCart(int id, int quantity, Products p) {
        this.id = id;
        this.quantity = quantity;
        this.p = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "UserCart{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", p=" + p.getName() +
                '}';
    }
}
