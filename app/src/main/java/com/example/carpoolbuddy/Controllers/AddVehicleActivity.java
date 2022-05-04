package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpoolbuddy.Model.Vehicles.*;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddVehicleActivity extends AppCompatActivity {

    private FirebaseFirestore firestoreRef;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    private LinearLayout linearLayout;

    private Spinner sVehicleType;
    private String selectedType;

    private EditText txtModel;
    private EditText txtCapacity;
    private EditText txtBasePrice;

    private EditText txtBicycleType;
    private EditText txtWeight;
    private EditText txtWeightCapacity;
    private EditText txtRange;
    private EditText txtMaxAltitude;
    private EditText txtMaxAirSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        firestoreRef = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        linearLayout = findViewById(R.id.AddVehicle_linearLayout);

        sVehicleType = findViewById(R.id.AddVehicle_vehicleTypeSpinner);
        setUpSpinner();
    }

    public void setUpSpinner() {
        String[] userTypes = {Constants.BICYCLE, Constants.CAR, Constants.HELICOPTER, Constants.SEGWAY};

        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddVehicleActivity.this, android.R.layout.simple_spinner_item, userTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sVehicleType.setAdapter(langArrAdapter);

        sVehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedType = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addFields() {
        addCommonFields();

        switch(selectedType) {
            case Constants.BICYCLE:
                txtBicycleType = new EditText(this);
                txtBicycleType.setHint("Bicycle Type");
                linearLayout.addView(txtBicycleType);

                txtWeight = new EditText(this);
                txtWeight.setHint("Weight");
                linearLayout.addView(txtWeight);

                txtWeightCapacity = new EditText(this);
                txtWeightCapacity.setHint("Weight Capacity");
                linearLayout.addView(txtWeightCapacity);
                return;
            case Constants.CAR:
                txtRange = new EditText(this);
                txtRange.setHint("Range");
                linearLayout.addView(txtRange);
                return;
            case Constants.HELICOPTER:
                txtMaxAltitude = new EditText(this);
                txtMaxAltitude.setHint("Max Altitude");
                linearLayout.addView(txtMaxAltitude);

                txtMaxAirSpeed = new EditText(this);
                txtMaxAirSpeed.setHint("Mac Air Speed");
                linearLayout.addView(txtMaxAirSpeed);
                return;
            case Constants.SEGWAY:
                txtRange = new EditText(this);
                txtRange.setHint("Range");
                linearLayout.addView(txtRange);

                txtWeightCapacity = new EditText(this);
                txtWeightCapacity.setHint("Weight Capacity");
                linearLayout.addView(txtWeightCapacity);
                return;
            default:
                return;
        }
    }

    public void addCommonFields() {
        linearLayout.removeAllViewsInLayout();

        txtModel = new EditText(this);
        txtModel.setHint("Model");
        linearLayout.addView(txtModel);


        txtCapacity = new EditText(this);
        txtCapacity.setHint("Capacity");
        linearLayout.addView(txtCapacity);

        txtBasePrice = new EditText(this);
        txtBasePrice.setHint("Base Price");
        linearLayout.addView(txtBasePrice);
    }

    public boolean formValid() {
        if(txtModel.getText().toString().equals("")) {
            Toast.makeText(AddVehicleActivity.this, "Model is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(txtCapacity.getText().toString().equals("")) {
            Toast.makeText(AddVehicleActivity.this, "Capacity is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isPositiveInt(txtCapacity.getText().toString())) {
            Toast.makeText(AddVehicleActivity.this, "Capacity must be a positive integer", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(txtBasePrice.getText().toString().equals("")) {
            Toast.makeText(AddVehicleActivity.this, "Base price is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isPositiveNumeric(txtBasePrice.getText().toString())) {
            Toast.makeText(AddVehicleActivity.this, "Base price must be a positive number", Toast.LENGTH_SHORT).show();
            return false;
        }


        switch(selectedType) {
            case Constants.BICYCLE:
                if(txtBicycleType.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Bicycle type is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if(txtWeight.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Weight is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtWeight.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Weight must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if(txtWeightCapacity.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Weight capacity is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtWeightCapacity.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Weight capacity must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;

            case Constants.CAR:
                if(txtRange.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Range is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtRange.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Range must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;

            case Constants.HELICOPTER:
                if(txtMaxAltitude.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Max altitude is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtMaxAltitude.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Max altitude must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if(txtMaxAirSpeed.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Max air speed is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtMaxAirSpeed.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Max air speed must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;

            case Constants.SEGWAY:
                if(txtRange.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Range is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtRange.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Range must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if(txtWeightCapacity.getText().toString().equals("")) {
                    Toast.makeText(AddVehicleActivity.this, "Weight capacity is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!isPositiveInt(txtWeightCapacity.getText().toString())) {
                    Toast.makeText(AddVehicleActivity.this, "Weight capacity must be a positive integer", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;

            default:
                return false;
        }
    }

    private boolean isPositiveNumeric(String s) {
        return s.matches("\\d+(.\\d+)?");
    }

    private boolean isPositiveInt(String s) {
        return s.matches("\\d+");
    }

    public void addNewVehicle(View view) {
        if(!formValid()) {
            return;
        }

        String path = Constants.VEHICLE_PATH;
        DocumentReference newRideRef = firestoreRef.collection(path).document();
        String vehicleID = newRideRef.getId();

        String owner = mUser.getEmail();
        String model = txtModel.getText().toString();
        int capacity = Integer.parseInt(txtCapacity.getText().toString());
        double basePrice = Double.parseDouble(txtBasePrice.getText().toString());

        Vehicle vehicle = null;
        ArrayList<String> ridersUIDs = new ArrayList<>();

        switch(selectedType) {
            case Constants.BICYCLE: {
                String bicycleType = txtBicycleType.getText().toString();
                int weight = Integer.parseInt(txtWeight.getText().toString());
                int weightCapacity = Integer.parseInt(txtWeightCapacity.getText().toString());
                vehicle = new Bicycle(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, bicycleType, weight, weightCapacity);
                break;
            }
            case Constants.CAR: {
                int range = Integer.parseInt(txtRange.getText().toString());
                vehicle = new Car(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, range);
                break;
            }
            case Constants.HELICOPTER: {
                int maxAltitude = Integer.parseInt(txtMaxAltitude.getText().toString());
                int maxAirSpeed = Integer.parseInt(txtMaxAirSpeed.getText().toString());
                vehicle = new Helicopter(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, maxAltitude, maxAirSpeed);
                break;
            }
            case Constants.SEGWAY: {
                int range = Integer.parseInt(txtRange.getText().toString());
                int weightCapacity = Integer.parseInt(txtWeightCapacity.getText().toString());
                vehicle = new Segway(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, range, weightCapacity);
                break;
            }
            default:
                return;
        }

        try{
            newRideRef.set(vehicle);
            Toast.makeText(AddVehicleActivity.this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(AddVehicleActivity.this, "Error adding vehicle", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}