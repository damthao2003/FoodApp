package com.example.foodorderingapp.model.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ListFoodActivity extends AppCompatActivity {
    ActivityListFoodBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private RecyclerView rcvFoodList;
    private int categoryId;
    private String categoryname;
    private TextView categoryTitle;
    private TextView titleTxt , feeTxt;
    private ImageView pic;
    private String searchText;
    private boolean inSearch;
    private FoodListAdapter foodListAdapter;
    ArrayList<FoodDomain> foodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
       recyclerListFood();
        initList();

    }

    private void initList() {
//        foodName = findViewById(R.id.titleTxt);
//        fee = findViewById(R.id.feeTxt);
//        foodPic = findViewById(R.id.img);
//        rcvFoodList = findViewById(R.id.foodListView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Food");
//        binding.progressBar2.setVisibility(View.VISIBLE);
        foodList = new ArrayList<>();

       Query query;
        if(inSearch){
            query = myRef.orderByChild("title").startAt(searchText).endAt(searchText+ '\uf8ff');
        }else{
            query = myRef.orderByChild("categoryId").equalTo(categoryId);
        }
       //Query query = myRef.orderByChild("categoryId").equalTo(categoryId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FoodDomain food = dataSnapshot.getValue(FoodDomain.class);
                        foodList.add(food);
//                        foodList.add(dataSnapshot.getValue(FoodDomain.class));
                    }
//                    if(foodList.size() > 0){
////                        rcvFoodList.setLayoutManager(new GridLayoutManager(ListFoodActivity.this, 2));
//                        binding.foodListView.setLayoutManager(new GridLayoutManager(ListFoodActivity.this, 2));
////                        foodListAdapter = new FoodListAdapter(foodList);
//                        adapterListFood = new FoodListAdapter(foodList);
////                        rcvFoodList.setAdapter(foodListAdapter);
//                        binding.foodListView.setAdapter(adapterListFood);
//                    }
//                    binding.progressBar2.setVisibility(View.GONE);
//                    foodListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListFoodActivity.this, "Get list food failed", Toast.LENGTH_SHORT).show();

            }
        });
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

    private  void recyclerListFood(){

        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.feeTxt);
        pic = findViewById(R.id.img);
        rcvFoodList = findViewById(R.id.foodListView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvFoodList.setLayoutManager(linearLayoutManager);

        foodList = new ArrayList<>();
        adapterListFood = new FoodAdapter(foodList);
        rcvFoodList.setAdapter(adapterListFood);

    }
}