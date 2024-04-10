package com.example.foodorderingapp.model.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import android.widget.Toast;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Adaptor.CategoryAdapter;
import com.example.foodorderingapp.model.Adaptor.FoodAdapter;
import com.example.foodorderingapp.model.Adaptor.FoodListAdapter;
import com.example.foodorderingapp.model.Domain.CategoryDomain;
import com.example.foodorderingapp.model.Domain.FoodDomain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
//    private RecyclerView.Adapter  adapter2;
//    private Adapter adapterCate, adapter2;
    private RecyclerView rcvCategoryList , rcvFoodList;
    private CategoryAdapter categoryAdapter;
    private FoodAdapter foodAdapter;
    private TextView cateName, foodname, fee;
    private ImageView catePic, foodPic;
    private ArrayList<CategoryDomain> categoryList;
    private ArrayList<FoodDomain> foodList;
    private ConstraintLayout mainLayout;
    String searchText;
    private Timer timer = new Timer();
    private final long DELAY_MILLIS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String displayName = user.getDisplayName();
            if (displayName != null) {
                TextView textView = findViewById(R.id.hi_txt);
                textView.setText("Chào mừng, " + displayName + "!");
            } else {
                // Xử lý lỗi không có tên hiển thị
                TextView textView = findViewById(R.id.hi_txt);
                 textView.setText("Chưa có tên hiển thị");
            }
        } else {
            // Hiển thị thông báo người dùng chưa đăng nhập
            // ...
            TextView textView = findViewById(R.id.hi_txt);
            textView.setText("Vui lòng đăng nhập");
        }

        recyclerViewCategory();
        getListCategory();

        recyclerViewFood();

        bottomNavigation();
        EditText editText = findViewById(R.id.searchBtn);
        Button searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ListFoodActivity.class);
                intent.putExtra("searchText", searchText);
                startActivity(intent);

                editText.clearFocus();
            }
        });
    }


    private void bottomNavigation(){
        LinearLayout cardBtn = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.cartBtn);
        ImageView imageView4 = findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
                startActivity(intent);
            }
        });

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
        categoryAdapter = new CategoryAdapter((categoryList));
        rcvCategoryList.setAdapter(categoryAdapter);


    }

    private void mainLayoutCate(){
        mainLayout.findViewById(R.id.mainLayout);


    }


    private void recyclerViewFood(){
        foodname = findViewById(R.id.foodName);
        foodPic = findViewById(R.id.foodPic);
        fee = findViewById(R.id.feeTxt);
        rcvFoodList = findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvFoodList.setLayoutManager(linearLayoutManager);

        foodList = new ArrayList<>();
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

    private void getListFood(String searchText){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
    int limit = 5;
        foodList.clear();  // Clear existing data before new search

        Query query;
        if (searchText.isEmpty()) {
            // No search term, fetch initial list
            query = myRef.limitToFirst(limit);
        } else {
            // Search based on title (case-insensitive)
            query = myRef.orderByChild("title").startAt(searchText.toLowerCase()).endAt(searchText.toLowerCase() + '\uf8ff');
        }

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FoodDomain food = dataSnapshot.getValue(FoodDomain.class);
                    foodList.add(food);
                }
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });


        // Read from the database
//        int limit = 5;
//        c1:
//        myRef.limitToFirst(limit).addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    FoodDomain food = dataSnapshot.getValue(FoodDomain.class);
//                    foodList.add(food);
//                }
//                foodAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Failed to read value
//                Toast.makeText(MainActivity.this, "Get list food failed", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });

    }



}