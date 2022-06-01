package com.example.carpoolbuddy.Controllers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Allows user to add vehicles to the firebase
 * @author Rikuto
 * @version 0.1
 */
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

    private Vehicle vehicle;

    private ImageView image;
    private Uri imageUri;
    private StorageReference storageRef;

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

        image = findViewById(R.id.AddVehicle_imageView);
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    /**
     * adds all the possile vehicle type to the spinner
     * Bicycle, Car, Helicopter, Segway
     * adds all the necessary fields for selected vehicle type
     */
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

    /**
     * text fields are added depending on the slected vehicle type
     */
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
                txtMaxAirSpeed.setHint("Max Air Speed");
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

    /**
     * common fields are added
     * Model, Capacity, Base Price
     */
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

    /**
     * checks if the form is valid
     * whether the input is empty or not
     * whether the input is numeric/positive integer or not
     * @return true if valid
     */
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

    /**
     * checks if a given string is a positive number
     * @param s input string
     * @return true if positive number
     */
    private boolean isPositiveNumeric(String s) {
        return s.matches("\\d+(.\\d+)?");
    }

    /**
     * checks if a given string is a positive integer or not
     * @param s input string
     * @return true if positive int
     */
    private boolean isPositiveInt(String s) {
        return s.matches("\\d+");
    }

    /**
     * adds new vehicle to the firebase
     * @param view invoked when clicking add button
     */
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

        ArrayList<String> ridersUIDs = new ArrayList<>();

        switch(selectedType) {
            case Constants.BICYCLE: {
                String bicycleType = txtBicycleType.getText().toString();
                int weight = Integer.parseInt(txtWeight.getText().toString());
                int weightCapacity = Integer.parseInt(txtWeightCapacity.getText().toString());
                vehicle = new Bicycle(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, null, bicycleType, weight, weightCapacity);
                break;
            }
            case Constants.CAR: {
                int range = Integer.parseInt(txtRange.getText().toString());
                vehicle = new Car(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, null, range);
                break;
            }
            case Constants.HELICOPTER: {
                int maxAltitude = Integer.parseInt(txtMaxAltitude.getText().toString());
                int maxAirSpeed = Integer.parseInt(txtMaxAirSpeed.getText().toString());
                vehicle = new Helicopter(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, null, maxAltitude, maxAirSpeed);
                break;
            }
            case Constants.SEGWAY: {
                int range = Integer.parseInt(txtRange.getText().toString());
                int weightCapacity = Integer.parseInt(txtWeightCapacity.getText().toString());
                vehicle = new Segway(owner, model, capacity, vehicleID, ridersUIDs, true, selectedType, basePrice, null, range, weightCapacity);
                break;
            }
            default:
                return;
        }

        try{
            if(imageUri != null) {
                addImage();
            }

            newRideRef.set(vehicle);
            Toast.makeText(AddVehicleActivity.this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(AddVehicleActivity.this, "Error adding vehicle", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * user can select images from their gallery
     * @param view invoked when image icon is clicked
     */
    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    /**
     * stores the imageUri when image selected
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    /**
     * adds image to the firebase storage
     */
    public void addImage() {
        String imageID = UUID.randomUUID().toString();
        StorageReference imageRef = storageRef.child(Constants.IMAGE_PATH + imageID);
        vehicle.setImageID(imageID);

        imageRef.putFile(imageUri).addOnCompleteListener(
            (task) -> {
                if(task.isSuccessful()) {
                    Toast.makeText(AddVehicleActivity.this, "Image Added Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddVehicleActivity.this, "Error adding images", Toast.LENGTH_SHORT).show();
                }

        });
    }
}