package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

public class Student extends User {
    private String graduatingYear;
    private ArrayList<String> parentUIDs;

    public Student() {
        super();
    }

    public Student(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String graduatingYear, ArrayList<String> parentUIDs) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.graduatingYear = graduatingYear;
        this.parentUIDs = parentUIDs;
    }

    public String getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(String graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList<String> getParentUIDs() {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList<String> parentUIDs) {
        this.parentUIDs = parentUIDs;
    }

    public void addParentUID(String parentUID) {
        parentUIDs.add(parentUID);
    }

    @Override
    public String toString() {
        return "Student{" +
                "userID='" + getUserID() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", userType='" + getUserType() + '\'' +
                ", priceMultiplier=" + getPriceMultiplier() +
                ", ownedVehicles=" + getOwnedVehicles() +
                ", graduatingYear='" + graduatingYear + '\'' +
                ", parentUIDs=" + parentUIDs +
                '}';
    }
}
