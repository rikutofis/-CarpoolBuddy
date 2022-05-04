package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpoolbuddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    public void logOut(View view) {
        mAuth.signOut();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void addVehicles(View view) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }

    public void seeVehicles(View view) {
        startActivity(new Intent(this, VehiclesInfoActivity.class));
    }

}