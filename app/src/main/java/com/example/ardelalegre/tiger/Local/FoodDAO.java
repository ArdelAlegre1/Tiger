package com.example.ardelalegre.tiger.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Flowable;
import com.example.ardelalegre.tiger.Model.Food;
import java.util.List;

@Dao
public interface FoodDAO {

    @Query("SELECT * FROM  foods WHERE id=:foodId")
    Flowable<Food> getFoodById(int foodId);

    @Query("SELECT * FROM foods")
    Flowable<List<Food>> getAllFoods();

    @Insert
    void insertFood(Food... foods);

    @Update
    void updateFood(Food... foods);

    @Delete
    void deleteFood(Food food);

    @Query("DELETE FROM foods")
    void deleteAllFoods();

}
