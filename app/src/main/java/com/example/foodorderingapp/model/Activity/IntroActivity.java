package com.example.foodorderingapp.model.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_intro);

        setVariable();
//        getWindow().setStatusBarColor(Color.parseColor("FFE4B5"));

    }
    private void setVariable(){
        binding.lognInBtn.setOnClickListener(v ->{
            if(mAuth.getCurrentUser() != null){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }else{
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));

            }
        });
    }

}