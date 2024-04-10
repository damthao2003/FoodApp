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
    private ImageView plusBtn, minusBtn, picFood;

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
                .into(picFood);


        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" +object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));


        plusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                numberOrder=numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }

        });
        minusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(numberOrder>1){
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }

        });
        addToCartBtn=findViewById(R.id.addToCartBtn);

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
        titleTxt=findViewById(R.id.foodTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOderTxt);
        plusBtn=findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);
    }
}