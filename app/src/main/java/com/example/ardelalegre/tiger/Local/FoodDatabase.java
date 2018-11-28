package com.example.ardelalegre.tiger.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ardelalegre.tiger.Model.Food;

import static com.example.ardelalegre.tiger.Local.FoodDatabase.DATABASE_VERSION;


@Database(entities = Food.class, version = DATABASE_VERSION)
public abstract class FoodDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EDMT-Database-Room";

    public abstract FoodDAO foodDAO();

    private static FoodDatabase mInstance;

    public static FoodDatabase getInstance(Context context)
    {
        if(mInstance == null) {
            mInstance = Room.databaseBuilder(context, FoodDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }
}
