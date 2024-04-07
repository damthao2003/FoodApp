package com.example.foodorderingapp.model.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorderingapp.databinding.ActivitySignUpBinding;
//import com.example.login.R;
//import com.example.login.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends BaseActivity {
    ActivitySignUpBinding binding;
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
    }
    private void setVariable(){
        binding.signupBtn.setOnClickListener(v -> {
            String email=binding.userEdit.getText().toString();
            String password=binding.passEdit.getText().toString();

            if(password.length()<6){
                Toast toast = Toast.makeText(getApplicationContext(), "This is a short Toast message", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "onComplete: ");
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        } else {
                            Log.i(TAG, "failure: "+task.getException());
                            Toast.makeText(SignUpActivity.this,"Authentication",Toast.LENGTH_SHORT).show();
                        }
                    });




        });

    }
}