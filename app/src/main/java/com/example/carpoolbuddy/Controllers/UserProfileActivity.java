package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpoolbuddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Allows user to navigate through different functions
 * See Vehicles, Add Vehicles, Logout
 * @author Rikuto
 * @version 0.1
 */
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

    /**
     * logout
     * @param view invoked when logout button clicked
     */
    public void logOut(View view) {
        mAuth.signOut();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    /**
     * takes the user to AddVehicleActivity page
     * @param view invoked when add vehicle button clicked
     */
    public void addVehicles(View view) {
        startActivity(new Intent(this, AddVehicleActivity.class));
    }

    /**
     * takes the user to VehiclesInfoActivity
     * @param view invoked when see vehicle button clicked
     */
    public void seeVehicles(View view) {
        startActivity(new Intent(this, VehiclesInfoActivity.class));
    }

}