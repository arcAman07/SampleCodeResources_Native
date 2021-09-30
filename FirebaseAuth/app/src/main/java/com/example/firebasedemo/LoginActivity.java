package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    ProgressBar progressBar;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mauth = FirebaseAuth.getInstance();
    }
    public void onClick(View view) {
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(mail.isEmpty()) {
            email.setError("Email address is required!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Provide a valid email address");
            email.requestFocus();
            return;

        }
        if(pass.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if(pass.length()<8) {
            password.setError("More than 8 characters password is required");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mauth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),ChatPage.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}