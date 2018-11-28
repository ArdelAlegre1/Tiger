package com.example.ardelalegre.tiger.DataBase;

import com.example.ardelalegre.tiger.Model.Food;

import java.util.List;

import io.reactivex.Flowable;

public interface IFoodDataSource {

    Flowable<Food> getFoodById(int foodId);
    Flowable<List<Food>> getAllFoods();
    void insertFood(Food... foods);
    void updateFood(Food... foods);
    void deleteFood(Food food);
    void deleteAllFoods();

}

