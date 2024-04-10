package com.example.foodorderingapp.model.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ActivityIntroBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    TextView tvLogin;
    TextView tvSignup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        tvLogin= findViewById(R.id.tvlogin);
        tvSignup=findViewById(R.id.tvsignup);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() != null){
                    Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    //Toast.makeText(IntroActivity.this, "sdsdfsdfs", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        onStart();

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Người dùng đã đăng nhập trước đó, tự động đăng nhập lại
            // Ví dụ: chuyển người dùng đến trang chủ
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Tùy chọn: kết thúc Activity hiện tại sau khi chuyển đến trang chủ
        }


    }

}