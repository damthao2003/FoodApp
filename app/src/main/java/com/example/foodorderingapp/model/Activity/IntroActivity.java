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
//    private ConstraintLayout signUpBtn, lognInBtn;
    ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_intro);

        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#FFD500"));


    }

    private void setVariable(){
        binding.lognInBtn.setOnClickListener(view -> {
            if(mAuth.getCurrentUser() != null){
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }else{
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }

        });

        binding.signUpBtn.setOnClickListener(view -> startActivity(new Intent(IntroActivity.this, SignUpActivity.class)));

    }

}