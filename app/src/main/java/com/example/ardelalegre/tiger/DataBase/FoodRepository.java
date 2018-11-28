package com.example.ardelalegre.tiger.DataBase;

import com.example.ardelalegre.tiger.Model.Food;

import java.util.List;

import io.reactivex.Flowable;

public class FoodRepository implements IFoodDataSource{

    private IFoodDataSource mLocalDatasource;
    private static FoodRepository mInstance;


    public FoodRepository(IFoodDataSource mLocalDatasource) {
        this.mLocalDatasource = mLocalDatasource;
    }

    public static FoodRepository getInstance(IFoodDataSource mLocalDatasource) {
        if(mInstance == null) {
            mInstance = new FoodRepository(mLocalDatasource);
        }
        return mInstance;
    }

    @Override
    public Flowable<Food> getFoodById(int foodId) {
        return mLocalDatasource.getFoodById(foodId);
    }

    @Override
    public Flowable<List<Food>> getAllFoods() {
        return mLocalDatasource.getAllFoods();
    }

    @Override
    public void insertFood(Food... foods) {
        mLocalDatasource.insertFood();
    }

    @Override
    public void updateFood(Food... foods) {
        mLocalDatasource.updateFood();
    }

    @Override
    public void deleteFood(Food food) {
        mLocalDatasource.deleteFood(food);
    }

    @Override
    public void deleteAllFoods() {
        mLocalDatasource.deleteAllFoods();
    }
}
