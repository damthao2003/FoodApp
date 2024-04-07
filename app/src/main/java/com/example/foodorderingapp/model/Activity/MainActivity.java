package com.example.foodorderingapp.model.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Adaptor.CategoryAdaptor;
import com.example.foodorderingapp.model.Adaptor.FoodAdaptor;
import com.example.foodorderingapp.model.Domain.CategoryDomain;
import com.example.foodorderingapp.model.Domain.FoodDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList , recyclerViewFoodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewFood();
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza","cat_1"));
        category.add(new CategoryDomain("Coca","cat_2"));
        category.add(new CategoryDomain("Trà sữa","cat_3"));
        category.add(new CategoryDomain("Hotdog","cat_4"));
        category.add(new CategoryDomain("Donut","cat_5"));
        category.add(new CategoryDomain("Burger","cat_6"));

        adapter = new CategoryAdaptor((category));
        recyclerViewCategoryList.setAdapter(adapter);

    }
    private void recyclerViewFood(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerView2);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> food = new ArrayList<>();
        food.add(new FoodDomain("Pizza hải sản", "pizza2", "Pizza hải sản,phô mai tan chảy,hành tây,mực",250.000, 10 ));
        food.add(new FoodDomain("Hamburger", "burger1", "Burgur thịt, chesse , cà chua, rau xà lạch", 20.000,15 ));
        food.add(new FoodDomain("Coca", "coca1", "coca có đường", 10.0000,20 ));
        food.add(new FoodDomain("Trà sữa thái", "milk_tea1", "Trà sữa thái thơm mùi trà, ngậy , ngọt vừa", 25.000,30 ));
        food.add(new FoodDomain("Hotdog", "hotdog2", "Hotdog nóng, ăn ngay", 20.000,15 ));
        food.add(new FoodDomain("Bánh Donut", "donut1", "Donut sô cô la, dâu, cam, ...",10.000 ,30 ));

        adapter2 = new FoodAdaptor(food);
        recyclerViewFoodList.setAdapter(adapter2);


    }

}