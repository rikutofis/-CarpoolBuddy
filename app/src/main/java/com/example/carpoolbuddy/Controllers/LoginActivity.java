package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpoolbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.SQLOutput;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        txtEmail = (EditText) findViewById(R.id.Login_txtEmail);
        txtPassword = (EditText) findViewById(R.id.Login_txtPassword);

        System.out.println("Hello");
        Toast.makeText(this, "Log in success", Toast.LENGTH_SHORT).show();
    }

    private boolean formValid() {
        if(txtEmail.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(txtPassword.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void logIn(View view){
        if(!formValid()) {
            return;
        }

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                (task) -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "Log in: success");
                        Toast.makeText(LoginActivity.this, "Log in success", Toast.LENGTH_SHORT).show();

                        FirebaseUser user = mAuth.getCurrentUser();
                        goToUserProfileActivity();
                    }
                    else{
                        Log.w(TAG, "Log in: failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void signUp(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void goToUserProfileActivity(){
        startActivity(new Intent(this, UserProfileActivity.class));
    }
}