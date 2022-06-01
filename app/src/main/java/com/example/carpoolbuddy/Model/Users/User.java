package com.example.carpoolbuddy.Model.Users;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Parent class of all users
 * @author Rikuto
 * @version 0.1
 */
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

    /**
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID sets userID to parameter
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets name to parameter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email sets email to parameter
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType sets user type to parameter
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return price multiplier
     */
    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    /**
     * @param priceMultiplier sets price multiplier to parameter
     */
    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    /**
     * @return owned vehicles
     */
    public ArrayList<String> getOwnedVehicles() {
        return ownedVehicles;
    }

    /**
     * @param ownedVehicles sets ownedVehicles to parameter
     */
    public void setOwnedVehicles(ArrayList<String> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }

    /**
     * @param vehicle adds vehicle to ownedVehicles
     */
    public void addOwnedVehicle(String vehicle) {
        ownedVehicles.add(vehicle);
    }

    /**
     * @return string of all the information in the object
     */
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

