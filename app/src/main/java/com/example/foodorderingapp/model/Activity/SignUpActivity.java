package com.example.foodorderingapp.model.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ActivitySignUpBinding;
//import com.example.login.R;
//import com.example.login.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends BaseActivity {
    ActivitySignUpBinding binding;
    TextView tvLG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_sign_up);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        setVariable();
        tvLG=findViewById(R.id.tvlg);
        tvLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setVariable(){
        binding.signupBtn.setOnClickListener(v -> {
            String email=binding.userEdit.getText().toString();
            String password=binding.passEdit.getText().toString();
            String ten =binding.tenEdit.getText().toString();

            if(password.length()<6){
                Toast toast = Toast.makeText(getApplicationContext(), "Hãy nhập mật khẩu dài hơn 6 ký tự", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                     .addOnCompleteListener(SignUpActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "onComplete: ");
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(ten)
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Tên hiển thị cập nhật thành công");
                                            }
                                        }
                                    });

                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        } else {
                            Log.i(TAG, "failure: "+task.getException());
                            Toast.makeText(SignUpActivity.this,"Tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
                        }
                    });




        });

    }
}