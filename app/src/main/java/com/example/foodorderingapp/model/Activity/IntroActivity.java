package com.example.foodorderingapp.model.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ActivityIntroBinding;
import com.example.foodorderingapp.model.Adaptor.CategoryAdaptor;
import com.example.foodorderingapp.model.Adaptor.FoodAdaptor;
import com.example.foodorderingapp.model.Domain.CategoryDomain;
import com.example.foodorderingapp.model.Domain.FoodDomain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

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
<<<<<<< HEAD
=======


>>>>>>> 59c8df33ba2ebfa25a17c18fc6e36d1c4d59f0c4
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this,SignUpActivity.class);
                startActivity(intent);

<<<<<<< HEAD
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
=======

            }
        });
>>>>>>> 59c8df33ba2ebfa25a17c18fc6e36d1c4d59f0c4
    }



}