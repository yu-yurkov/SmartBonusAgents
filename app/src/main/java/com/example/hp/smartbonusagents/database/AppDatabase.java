package com.example.hp.smartbonusagents.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.hp.smartbonusagents.dao.UserCartDao;
import com.example.hp.smartbonusagents.entities.UserCartEntity;
import com.example.hp.smartbonusagents.model.UserCart;

@Database(entities = {UserCartEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserCartDao userCartDao();

}
