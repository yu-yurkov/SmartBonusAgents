package com.example.hp.smartbonusagents.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.hp.smartbonusagents.model.Products;

@Entity
public class UserCartEntity {
    @PrimaryKey (autoGenerate = true)
    public int id;

    public int quantity;

    @Embedded (prefix = "products")
    public Products products;

}
