package com.example.foodorderingapp.model.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ActivityListFoodBinding;
import com.example.foodorderingapp.model.Adaptor.FoodAdapter;
import com.example.foodorderingapp.model.Adaptor.FoodListAdapter;
import com.example.foodorderingapp.model.Domain.FoodDomain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class ListFoodActivity extends AppCompatActivity {
    ActivityListFoodBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryname;
    private String searchText;
    private boolean inSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getIntentExtra();
//        searchText = getIntent().getStringExtra("searchText");
        initList();
        Intent intent = getIntent();
        String searchText = intent.getStringExtra("searchText");


    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId", 0);
        categoryname= getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        inSearch=getIntent().getBooleanExtra("isSearch", false);

        binding.titleTxt.setText(categoryname);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initList() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
        binding.progressBar2.setVisibility(View.VISIBLE);
        ArrayList<FoodDomain> foodList = new ArrayList<>();

        Query query;
        if (searchText != null && !searchText.isEmpty()) {
            // Case-insensitive search by title
            query = myRef.orderByChild("title").startAt(searchText.toLowerCase()).endAt(searchText.toLowerCase() + "\uf8ff");
        } else {
            // Default behavior based on category
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();  // Clear existing data before adding new
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FoodDomain food = dataSnapshot.getValue(FoodDomain.class);
                    foodList.add(food);
                }

                // Update adapter and UI
                adapterListFood = new FoodListAdapter(foodList);
                binding.foodListView.setAdapter(adapterListFood);
                binding.progressBar2.setVisibility(View.GONE);
//                if(snapshot.exists()){
//                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                        foodList.add(dataSnapshot.getValue(FoodDomain.class));
//                    }
//                    if(foodList.size() > 0){
//                        binding.foodListView.setLayoutManager(new GridLayoutManager(ListFoodActivity.this, 2));
//                        adapterListFood = new FoodListAdapter(foodList);
//                        binding.foodListView.setAdapter(adapterListFood);
//                    }
//                    binding.progressBar2.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}