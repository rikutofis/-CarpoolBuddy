package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

public class Parent extends User {
    private ArrayList<String> childrenUIDs;

    public Parent() {
        super();
    }

    public Parent(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, ArrayList<String> childrenUIDs) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.childrenUIDs = childrenUIDs;
    }

    public ArrayList<String> getChildrenUIDs() {
        return childrenUIDs;
    }

    public void setChildrenUIDs(ArrayList<String> childrenUIDs) {
        this.childrenUIDs = childrenUIDs;
    }

    public void addChildrenUID(String childrenUID) {
        childrenUIDs.add(childrenUID);
    }

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
