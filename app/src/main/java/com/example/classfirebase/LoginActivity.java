package com.example.classfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText,passwordEditText;
    private Button loginButton,googleLoginButton;
    private TextView textView;
    private ProgressBar progressBar;

    // firebase
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findSection();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

                    if(password.length()>=6){

                        userLogin(email,password);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Password should at least 6 character", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(LoginActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userLogin(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser currentUser= firebaseAuth.getCurrentUser();
                    try {

                        if(currentUser.isEmailVerified()){
                            progressBar.setVisibility(View.GONE);
                            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK); // clear the activity stack...
                            startActivity(intent);

                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Mail Not Verified", Toast.LENGTH_SHORT).show();
                        }

                    }catch (NullPointerException e){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void findSection() {
        emailEditText= findViewById(R.id.loginEmailId);
        passwordEditText= findViewById(R.id.loginPasswordId);
        loginButton= findViewById(R.id.loginButtonId);
        googleLoginButton= findViewById(R.id.googleLoginButtonId);
        textView= findViewById(R.id.signInTextId);
        progressBar= findViewById(R.id.loginProgressId);
        progressBar.setVisibility(View.GONE);

    }
}