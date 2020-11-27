package com.example.classfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    //private FirebaseDatabase database= FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser == null){
            gotToLoginActivity();
        }
        else {
            gotToMainActivity();
        }

    }

    private void gotToMainActivity() {
        Intent intent= new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotToLoginActivity() {
        Intent intent= new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}