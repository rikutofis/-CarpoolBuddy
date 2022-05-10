package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpoolbuddy.Model.Users.*;
import com.example.carpoolbuddy.R;
import com.example.carpoolbuddy.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AuthActivity";

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;

    private LinearLayout linearLayout;

    private Spinner sUserType;
    private String selectedRole;

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;

    private EditText txtChildrenUIDs;
    private EditText txtInSchoolTitle;
    private EditText txtGraduateYear;
    private EditText txtGraduatingYear;
    private EditText parentUIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        linearLayout = findViewById(R.id.Auth_linearLayout);

        sUserType = findViewById(R.id.Auth_userTypeSpinner);
        setUpSpinner();
    }

    public void setUpSpinner() {
        String[] userTypes = {Constants.STUDENT, Constants.TEACHER, Constants.PARENT, Constants.ALUMNI};

        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<>(AuthActivity.this, android.R.layout.simple_spinner_item, userTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sUserType.setAdapter(langArrAdapter);

        sUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addFields() {
        addCommonFields();

        switch(selectedRole) {
            case Constants.STUDENT:
                txtGraduatingYear = new EditText(this);
                txtGraduatingYear.setHint("Graduating Year");
                linearLayout.addView(txtGraduatingYear);
                return;
            case Constants.TEACHER:
                txtInSchoolTitle = new EditText(this);
                txtInSchoolTitle.setHint("In School Title");
                linearLayout.addView(txtInSchoolTitle);
                return;
            case Constants.ALUMNI:
                txtGraduateYear = new EditText(this);
                txtGraduateYear.setHint("Graduate Year");
                linearLayout.addView(txtGraduateYear);
                return;
            case Constants.PARENT:
                txtChildrenUIDs = new EditText(this);
                txtChildrenUIDs.setHint("Children User ID");
                linearLayout.addView(txtChildrenUIDs);
                return;
            default:
                return;
        }
    }

    public void addCommonFields() {
        linearLayout.removeAllViewsInLayout();

        txtName = new EditText(this);
        txtName.setHint("Name");
        linearLayout.addView(txtName);


        txtEmail = new EditText(this);
        txtEmail.setHint("Email");
        linearLayout.addView(txtEmail);

        txtPassword = new EditText(this);
        txtPassword.setHint("Password");
        linearLayout.addView(txtPassword);
    }


    public void signUp(View view){
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                (task) -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "Sign up: success");
                        Toast.makeText(AuthActivity.this, "Success", Toast.LENGTH_LONG).show();

                        mUser = mAuth.getCurrentUser();
                        addUser();
                        goToUserProfileActivity();
                    }
                    else{
                        Log.w(TAG, "Sign up: failure", task.getException());
                        Toast.makeText(AuthActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addUser() {
        String email = mUser.getEmail();
        String name = txtName.getText().toString();

        User user = null;
        ArrayList<String> ownedVehicles = new ArrayList<>();

        switch(selectedRole) {
            case "Student":
                String graduatingYear = txtGraduatingYear.getText().toString();
                user = new Student(email, name, email, selectedRole, 10, ownedVehicles, graduatingYear, null);
                break;

            case "Teacher":
                String inSchoolTitle = txtInSchoolTitle.getText().toString();
                user = new Teacher(email, name, email, selectedRole, 10, ownedVehicles, inSchoolTitle);
                break;

            case "Alumni":
                String graduateYear = txtGraduateYear.getText().toString();
                user = new Alumni(email, name, email, selectedRole, 10, ownedVehicles, graduateYear);
                break;

            case "Parent":
                ArrayList<String> childrenUIDs = new ArrayList<>();
                String childrenUID = txtChildrenUIDs.getText().toString();
                childrenUIDs.add(childrenUID);
                user = new Parent(email, name, email, selectedRole, 10, ownedVehicles, childrenUIDs);
                break;

            default:
                return;
        }

        try {
            firestore.collection(Constants.USER_PATH).document(email).set(user);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void goToUserProfileActivity() {
        startActivity(new Intent(this, UserProfileActivity.class));
    }
}