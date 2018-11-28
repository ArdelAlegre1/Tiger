package com.example.ardelalegre.tiger.Local;

import com.example.ardelalegre.tiger.DataBase.IFoodDataSource;
import com.example.ardelalegre.tiger.Model.Food;

import java.util.List;

import io.reactivex.Flowable;

public class FoodDataSource implements IFoodDataSource {

    private FoodDAO foodDAO;
    private static FoodDataSource mInstance;

    public FoodDataSource(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public static FoodDataSource getInstance(FoodDAO foodDAO) {
        if(mInstance == null) {
            mInstance = new FoodDataSource(foodDAO);
        }
        return mInstance;
    }


    @Override
    public Flowable<Food> getFoodById(int foodId) {
        return foodDAO.getFoodById(foodId);
    }

    @Override
    public Flowable<List<Food>> getAllFoods() {
        return foodDAO.getAllFoods();
    }

    @Override
    public void insertFood(Food... foods) {
        foodDAO.insertFood(foods);
    }

    @Override
    public void updateFood(Food... foods) {
        foodDAO.updateFood(foods);
    }

    @Override
    public void deleteFood(Food food) {
        foodDAO.deleteFood(food);
    }

    @Override
    public void deleteAllFoods() {
        foodDAO.deleteAllFoods();
    }
}
