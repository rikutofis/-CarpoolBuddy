package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

/**
 * Child of User
 * @author Rikuto
 * @version 0.1
 */
public class Parent extends User {
    private ArrayList<String> childrenUIDs;

    public Parent() {
        super();
    }

    public Parent(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> childrenUIDs) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.childrenUIDs = childrenUIDs;
    }

    /**
     * @return childrenUIDs
     */
    public ArrayList<String> getChildrenUIDs() {
        return childrenUIDs;
    }

    /**
     * @param childrenUIDs sets childrenUIDs to parameter
     */
    public void setChildrenUIDs(ArrayList<String> childrenUIDs) {
        this.childrenUIDs = childrenUIDs;
    }

    /**
     * @param childrenUID adds parameter to childrenUIDs
     */
    public void addChildrenUID(String childrenUID) {
        childrenUIDs.add(childrenUID);
    }

    /**
     * @return string of all the information in the object
     */
    @Override
    public String toString() {
        return "Parent{" +
                "userID='" + getUserID() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", userType='" + getUserType() + '\'' +
                ", priceMultiplier=" + getPriceMultiplier() +
                ", ownedVehicles=" + getOwnedVehicles() +
                ", childrenUIDs=" + childrenUIDs +
                '}';
    }
}
