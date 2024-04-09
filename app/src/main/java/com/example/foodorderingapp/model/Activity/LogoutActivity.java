package com.example.foodorderingapp.model.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.foodorderingapp.R;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogoutActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView Nametxt, Emailtxt, passwordtxt;
    private Button buttonLogout;
    private Button ButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mAuth = FirebaseAuth.getInstance();

        buttonLogout = findViewById(R.id.buttonLogout);
        ButtonBack = findViewById(R.id.buttonBack); // Ánh xạ nút Back
        Emailtxt = findViewById(R.id.Emailtxt); // Ánh xạ TextView Emailtxt
        passwordtxt = findViewById(R.id.passwordtxt); // Ánh xạ TextView passwordtxt
        Nametxt = findViewById(R.id.nametxt);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if(intent != null) {
            String email = intent.getStringExtra("userEdit");
            String password = intent.getStringExtra("passEdit");
            String name = intent.getStringExtra("nameEdit"); // Lấy tên người dùng từ Intent
            Emailtxt.setText(email); // Gán email vào TextView Emailtxt
            passwordtxt.setText(password); // Gán password vào TextView passwordtxt
            Nametxt.setText(name); // Gán tên người dùng vào TextView nameTxt
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại MainActivity
                Intent intent = new Intent(LogoutActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc Activity hiện tại
            }
        });
    }

    private void logoutUser() {
        mAuth.signOut();
        Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            intent.putExtra("email", email);
        }

        // Khởi chạy Intent
        startActivity(intent);
        finish();
    }
}
