package com.example.ardelalegre.tiger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ardelalegre.tiger.DataBase.FoodRepository;
import com.example.ardelalegre.tiger.Local.FoodDataSource;
import com.example.ardelalegre.tiger.Local.FoodDatabase;
import com.example.ardelalegre.tiger.Model.Food;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ScrollingActivity2 extends AppCompatActivity {

    private ListView listFood;
    private FloatingActionButton fab;

    //Adapter
    List<Food> foodList = new ArrayList<>();
    ArrayAdapter adapter;

    //Database
    private CompositeDisposable compositeDisposable;
    private FoodRepository foodRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init
        compositeDisposable = new CompositeDisposable();
        //Init View
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listFood = (ListView) findViewById(R.id.listFood);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);
        registerForContextMenu(listFood);
        listFood.setAdapter(adapter);

        //Database
        FoodDatabase foodDatabase = FoodDatabase.getInstance(this);
        foodRepository = FoodRepository.getInstance(FoodDataSource.getInstance(foodDatabase.foodDAO()));

        //load data
        loadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        Food food = new Food("1234", "Food", "today");
                        foodRepository.insertFood(food);
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                            @Override
                            public void accept(Object o) throws Exception {
                                Toast.makeText(ScrollingActivity2.this, "Food added!", Toast.LENGTH_SHORT).show();
                                System.out.println("Hello world");
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(ScrollingActivity2.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                                new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        loadData();
                                    }

                                }

                        );
            }
        });
    }

    private void loadData() {
        //User RxJava
        Disposable disposable = foodRepository.getAllFoods()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Food>>() {
                    @Override
                    public void accept(List<Food> foods) throws Exception {
                        onGetAllFoodSuccess(foods);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(ScrollingActivity2.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void onGetAllFoodSuccess(List<Food> foods) {
        foodList.clear();
        foodList.addAll(foods);
        adapter.notifyDataSetChanged();
    }
}
