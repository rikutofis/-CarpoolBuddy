package com.example.carpoolbuddy.Model.Users;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userID;
    private String name;
    private String email;
    private String userType;
    private double priceMultiplier;
    private ArrayList<String> ownedVehicles;

    public User() {}

    public User(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.priceMultiplier = priceMultiplier;
        this.ownedVehicles = ownedVehicles;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public ArrayList<String> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(ArrayList<String> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }

    public void addOwnedVehicle(String vehicle) {
        ownedVehicles.add(vehicle);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", priceMultiplier=" + priceMultiplier +
                ", ownedVehicles=" + ownedVehicles +
                '}';
    }
}

