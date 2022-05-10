package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpoolbuddy.Model.Vehicles.*;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;

public class VehicleProfileActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    private LinearLayout linearLayout;

    private TextView txtModel;
    private TextView txtVehicleType;
    private TextView txtCapacity;
    private TextView txtBasePrice;

    private TextView txtBicycleType;
    private TextView txtWeight;
    private TextView txtWeightCapacity;
    private TextView txtRange;
    private TextView txtMaxAltitude;
    private TextView txtMaxAirSpeed;

    private Button btnBook;

    private Vehicle vehicle;

    private float textSize = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        linearLayout = findViewById(R.id.VehicleProfile_linearLayout);
        btnBook = findViewById(R.id.VehicleProfile_btnBook);

        if(getIntent().hasExtra(Constants.VEHICLE_PATH)){
            vehicle = getIntent().getParcelableExtra(Constants.VEHICLE_PATH);
        }

        if(mUser.getEmail().equals(vehicle.getOwner()) || vehicle.getRidersUIDs().contains(mUser.getEmail())) {
            btnBook.setEnabled(false);
        }

        addCommonFields();

        switch(vehicle.getVehicleType()) {
            case Constants.BICYCLE:
                addBicycle((Bicycle) vehicle);
                break;
            case Constants.CAR:
                addCar((Car) vehicle);
                break;
            case Constants.HELICOPTER:
                addHelicopter((Helicopter) vehicle);
                break;
            case Constants.SEGWAY:
                addSegway((Segway) vehicle);
                break;
            default:
                break;
        }
    }

    public void book(View view) {
        ArrayList<String> ridersUIDs = vehicle.getRidersUIDs();
        ridersUIDs.add(mUser.getEmail());

        try {
            firestore.collection(Constants.VEHICLE_PATH).document(vehicle.getVehicleID()).update(Constants.RIDERUID_PARAM, ridersUIDs);
            decrementCapacity();
            Toast.makeText(this, "Booked Successfully", Toast.LENGTH_SHORT).show();
            btnBook.setEnabled(false);
        }
        catch (Exception e) {
            Toast.makeText(this, "Error booking vehicle", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void decrementCapacity() {
        firestore.collection(Constants.VEHICLE_PATH).document(vehicle.getVehicleID()).get()
                .addOnCompleteListener(
                (task) -> {
                    if(task.getResult() == null) {
                    }
                    else if(task.isSuccessful()) {

                        try {
                            int capacity = task.getResult().get(Constants.CAPACITY_PARAM, Integer.class);
                            capacity--;
                            vehicle.setCapacity(capacity);
                            txtCapacity.setText("Capacity: "+capacity);
                            firestore.collection(Constants.VEHICLE_PATH).document(vehicle.getVehicleID()).update(Constants.CAPACITY_PARAM, capacity);

                            if(capacity == 0) {
                                firestore.collection(Constants.VEHICLE_PATH).document(vehicle.getVehicleID()).update(Constants.OPEN_PARAM, false);
                            }
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        task.getException().printStackTrace();
                        Toast.makeText(this, "Error booking vehicle", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addCommonFields() {
        txtVehicleType = new TextView(this);
        txtVehicleType.setTextSize(textSize);
        txtVehicleType.setText("Vehicle Type: "+vehicle.getVehicleType());
        linearLayout.addView(txtVehicleType);

        txtModel = new TextView(this);
        txtModel.setTextSize(textSize);
        txtModel.setText("Model: "+vehicle.getModel());
        linearLayout.addView(txtModel);

        txtCapacity = new TextView(this);
        txtCapacity.setTextSize(textSize);
        txtCapacity.setText("Capacity: "+vehicle.getCapacity());
        linearLayout.addView(txtCapacity);

        txtBasePrice = new TextView(this);
        txtBasePrice.setTextSize(textSize);
        txtBasePrice.setText("Base Price: €"+vehicle.getBasePrice());
        linearLayout.addView(txtBasePrice);
    }

    public void addBicycle(Bicycle bicycle) {
        txtBicycleType = new TextView(this);
        txtBicycleType.setTextSize(textSize);
        txtBicycleType.setText("Bicycle Type: "+bicycle.getBicycleType());
        linearLayout.addView(txtBicycleType);

        txtWeight = new TextView(this);
        txtWeight.setTextSize(textSize);
        txtWeight.setText("Weight: "+bicycle.getWeight());
        linearLayout.addView(txtWeight);

        txtWeightCapacity = new TextView(this);
        txtWeightCapacity.setTextSize(textSize);
        txtWeightCapacity.setText("Weight Capacity: "+bicycle.getWeightCapacity());
        linearLayout.addView(txtWeightCapacity);
    }

    public void addCar(Car car) {
        txtRange = new TextView(this);
        txtRange.setTextSize(textSize);
        txtRange.setText("Range: "+car.getRange());
        linearLayout.addView(txtRange);
    }

    public void addHelicopter(Helicopter helicopter) {
        txtMaxAltitude = new TextView(this);
        txtMaxAltitude.setTextSize(textSize);
        txtMaxAltitude.setText("Max Altitude: "+helicopter.getMaxAltitude());
        linearLayout.addView(txtMaxAltitude);

        txtMaxAirSpeed = new TextView(this);
        txtMaxAirSpeed.setTextSize(textSize);
        txtMaxAirSpeed.setText("Max Air Speed: "+helicopter.getMaxAirSpeed());
        linearLayout.addView(txtMaxAirSpeed);
    }

    public void addSegway(Segway segway) {
        txtRange = new TextView(this);
        txtRange.setTextSize(textSize);
        txtRange.setText("Range: "+segway.getRange());
        linearLayout.addView(txtRange);

        txtWeightCapacity = new TextView(this);
        txtWeightCapacity.setTextSize(textSize);
        txtWeightCapacity.setText("Weight Capacity: "+segway.getWeightCapacity());
        linearLayout.addView(txtWeightCapacity);
    }
}