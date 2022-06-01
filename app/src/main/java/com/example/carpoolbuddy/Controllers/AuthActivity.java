package com.example.carpoolbuddy.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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

/**
 * Allows user to create new account
 * @author Rikuto
 * @version 0.1
 */
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

    /**
     * adds all the possible users type to the spinner
     * changes the textfields depending on the selected user type
     * Student, Teacher, Parent, Alumni
     */
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

    /**
     * adds all the necessary fields for selected user type
     */
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

    /**
     * adds common fields
     * Name, Email, Password
     */
    public void addCommonFields() {
        linearLayout.removeAllViewsInLayout();

        txtName = new EditText(this);
        txtName.setHint("Name");
        linearLayout.addView(txtName);


        txtEmail = new EditText(this);
        txtEmail.setHint("Email");
        linearLayout.addView(txtEmail);

        txtPassword = new EditText(this);
        txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        txtPassword.setHint("Password");
        linearLayout.addView(txtPassword);
    }

    /**
     * signup function
     * authenticates email and password in firebase
     * tkaes the user to UserProfileActivity if success
     * @param view invoked when clicking signup button
     */
    public void signUp(View view){
        if(!formValid()) {
            return;
        }

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                (task) -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "Sign up: success");
                        Toast.makeText(AuthActivity.this, "Success", Toast.LENGTH_SHORT).show();

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

    /**
     * adds user to firebase
     */
    public void addUser() {
        String email = mUser.getEmail();
        String name = txtName.getText().toString();

        User user = null;
        ArrayList<String> ownedVehicles = new ArrayList<>();

        switch(selectedRole) {
            case Constants.STUDENT:
                String graduatingYear = txtGraduatingYear.getText().toString();
                user = new Student(email, name, email, selectedRole, 10, ownedVehicles, graduatingYear, null);
                break;

            case Constants.TEACHER:
                String inSchoolTitle = txtInSchoolTitle.getText().toString();
                user = new Teacher(email, name, email, selectedRole, 10, ownedVehicles, inSchoolTitle);
                break;

            case Constants.ALUMNI:
                String graduateYear = txtGraduateYear.getText().toString();
                user = new Alumni(email, name, email, selectedRole, 10, ownedVehicles, graduateYear);
                break;

            case Constants.PARENT:
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

    /**
     * takes the user to UserProfileActivity page
     */
    public void goToUserProfileActivity() {
        startActivity(new Intent(this, UserProfileActivity.class));
    }

    /**
     * checks if the input form is valid
     * @return true if valid
     */
    public boolean formValid() {
        if(txtName.getText().toString().equals("")) {
            Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(txtEmail.getText().toString().equals("")) {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!txtEmail.getText().toString().endsWith("@fis.edu")) {
            Toast.makeText(AuthActivity.this, "Only FIS community can sing up", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(txtPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        switch(selectedRole) {
            case Constants.STUDENT:
                if(txtGraduatingYear.getText().toString().equals("")) {
                    Toast.makeText(this, "Graduating Year is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case Constants.TEACHER:
                if(txtInSchoolTitle.getText().toString().equals("")) {
                    Toast.makeText(this, "In School Title is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case Constants.ALUMNI:
                if(txtGraduateYear.getText().toString().equals("")) {
                    Toast.makeText(this, "Graduate Year is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
            case Constants.PARENT:
                if(txtChildrenUIDs.getText().toString().equals("")) {
                    Toast.makeText(this, "Children UID is empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                break;
        }

        return true;
    }
}