package com.example.foodorderingapp.model.Activity;

import static androidx.recyclerview.widget.RecyclerView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Adaptor.CategoryAdapter;
import com.example.foodorderingapp.model.Adaptor.FoodAdapter;
import com.example.foodorderingapp.model.Domain.CategoryDomain;
import com.example.foodorderingapp.model.Domain.FoodDomain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter  adapter2;
//    private Adapter adapterCate, adapter2;
    private RecyclerView rcvCategoryList , rcvFoodList;
    private CategoryAdapter categoryAdapter;
    private FoodAdapter foodAdapter;

    private TextView cateName, foodname, fee;
    private ImageView catePic, foodPic;
    private ArrayList<CategoryDomain> categoryList;
    private ArrayList<FoodDomain> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        getListCategory();
          recyclerViewFood();
        bottomNavigation();
    }

    private  void initUi(){
    }
    private void bottomNavigation(){
        LinearLayout cardBtn = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.cartBtn);

        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory(){
        cateName = findViewById(R.id.cateName);
        catePic = findViewById(R.id.catePic);
        rcvCategoryList = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvCategoryList.setLayoutManager(linearLayoutManager);

        categoryList = new ArrayList<>();
//        categoryList.add(new CategoryDomain("Pizza","cat_1",1));
//        categoryList.add(new CategoryDomain("Burger","",2));
//        categoryList.add(new CategoryDomain("Coca", "",3));
//        categoryList.add(new CategoryDomain("Drink","",4));
//        categoryList.add(new CategoryDomain("Hotdog","",5));
//        categoryList.add(new CategoryDomain("Donut","",6));

        categoryAdapter = new CategoryAdapter((categoryList));
        rcvCategoryList.setAdapter(categoryAdapter);


    }
    private void recyclerViewFood(){
        foodname = findViewById(R.id.foodName);
        foodPic = findViewById(R.id.foodPic);
        fee = findViewById(R.id.fee);
        rcvFoodList = findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvFoodList.setLayoutManager(linearLayoutManager);

        foodList = new ArrayList<>();
//        food.add(new FoodDomain("Pizza hải sản", "pizza2", "Pizza hải sản,phô mai tan chảy,hành tây,mực","250.000", 10 ));
//        food.add(new FoodDomain("Hamburger", "burger1", "Burgur thịt, chesse , cà chua, rau xà lạch", "20.000",15 ));
//        food.add(new FoodDomain("Coca", "coca1", "coca có đường", "10.000",20 ));
//        food.add(new FoodDomain("Trà sữa thái", "milk_tea1", "Trà sữa thái thơm mùi trà, ngậy , ngọt vừa", "25.000",30 ));
//        food.add(new FoodDomain("Hotdog", "hotdog2", "Hotdog nóng, ăn ngay", "20.000",15 ));
//        food.add(new FoodDomain("Bánh Donut", "donut1", "Donut sô cô la, dâu, cam, ...","10.000" ,30 ));

        foodAdapter= new FoodAdapter(foodList);
        rcvFoodList.setAdapter(foodAdapter);


    }

    private void getListCategory(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Category");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    CategoryDomain cate = dataSnapshot.getValue(CategoryDomain.class);
                    categoryList.add(cate);
                }
                categoryAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Get list category failed", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void getListFood(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    FoodDomain food = dataSnapshot.getValue(FoodDomain.class);
                    foodList.add(food);
                }
                foodAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Get list food failed", Toast.LENGTH_SHORT).show();


            }
        });
    }


}