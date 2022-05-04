package com.example.carpoolbuddy.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.carpoolbuddy.Controllers.RecView.Rec_Adapter;
import com.example.carpoolbuddy.Model.Vehicles.*;
import com.example.carpoolbuddy.Model.Vehicles.Vehicle;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class VehiclesInfoActivity extends AppCompatActivity implements Rec_Adapter.OnViewClickListner{

    private static final String TAG = "VehiclesInfoActivity";

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private RecyclerView recView;

    private Vehicle vehicleInfo;
    private ArrayList<Vehicle> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_info);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        vehicleList = new ArrayList<>();

        recView = (RecyclerView) findViewById(R.id.VehiclesInfo_recView);

        getVehiclesFromDB();
    }

    public void getVehiclesFromDB() {
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();

        firestore.collection(Constants.VEHICLE_PATH).whereEqualTo("open", true).get()
                .addOnCompleteListener(this,
                (task) -> {
                    if(task.getResult() == null) {

                    }
                    else if(task.isSuccessful()) {
                        for(QueryDocumentSnapshot document : task.getResult()) {
                            vehicleList.add(document.toObject(Vehicle.class));
                        }
                        getAllRidesTask.setResult(null);
                    }
                    else{
                        Log.d(TAG, "Error getting vehicles from the database", task.getException());
                        Toast.makeText(this, "Error getting vehicles from the database", Toast.LENGTH_SHORT).show();
                    }
                });

        getAllRidesTask.getTask().addOnCompleteListener(
        (task) -> {
            recView.setAdapter(new Rec_Adapter(vehicleList, VehiclesInfoActivity.this));
            recView.setLayoutManager(new LinearLayoutManager(VehiclesInfoActivity.this));
        });
    }


    @Override
    public void onViewClick(int position) {
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();

        firestore.collection(Constants.VEHICLE_PATH).document(vehicleList.get(position).getVehicleID()).get()
                .addOnCompleteListener(this,
                (task) -> {
                    if(task.getResult() == null) {

                    }
                    else if(task.isSuccessful()) {

                        Class c = Vehicle.class;
                        try {
                            c = Class.forName(Constants.VEHICLE_PACKAGE + vehicleList.get(position).getVehicleType());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        Vehicle vehicle = (Vehicle) task.getResult().toObject(c);

                        Intent intent = new Intent(this, VehicleProfileActivity.class);
                        intent.putExtra(Constants.VEHICLE_PATH, (Parcelable) vehicle);
                        startActivity(intent);
                    }
                    else {
                        Log.d(TAG, "Error getting vehicle from the database", task.getException());
                        Toast.makeText(this, "Error getting vehicle from the database", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}