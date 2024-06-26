package com.example.foodorderingapp.model.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorderingapp.R;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    public String TAG="uilover";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        // Khởi tạo FirebaseApp
        FirebaseApp.initializeApp(this);
        
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}