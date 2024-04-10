package com.example.foodorderingapp.model.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Domain.FoodDomain;
import com.example.foodorderingapp.model.helper.ManagementCart;
import com.bumptech.glide.Glide;


public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusNumberFood;
    private ImageView minusNumberFood;
    private ImageView foodFic;


    private FoodDomain object;
    private ManagementCart managementCart;
//    private Object Glide;
    int numberOrder = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart=new ManagementCart(this);
        initView();
        getBundle();
    }

     private void getBundle() {

        object= (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(foodFic);


        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getFee() + "VNĐ");
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusNumberFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                numberOrder=numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });


        minusNumberFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(numberOrder>1){
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }

        });



        addToCartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
                Intent intent = new Intent(ShowDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }

    private void initView(){
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOderTxt);
        plusNumberFood=findViewById(R.id.plusNumberBtn);
        minusNumberFood = findViewById(R.id.minusNumberBtn);

        foodFic = findViewById(R.id.picFood);
    }
}