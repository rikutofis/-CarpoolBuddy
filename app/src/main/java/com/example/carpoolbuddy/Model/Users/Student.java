package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

/**
 * Child of User
 * @author Rikuto
 * @version 0.1
 */
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

    /**
     * @return graduating year
     */
    public String getGraduatingYear() {
        return graduatingYear;
    }

    /**
     * @param graduatingYear sets graduating year to parameter
     */
    public void setGraduatingYear(String graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    /**
     * @return parentUIDs
     */
    public ArrayList<String> getParentUIDs() {
        return parentUIDs;
    }

    /**
     * @param parentUIDs sets parentUIDs to parameter
     */
    public void setParentUIDs(ArrayList<String> parentUIDs) {
        this.parentUIDs = parentUIDs;
    }

    /**
     * @param parentUID adds parameter to parentUIDs
     */
    public void addParentUID(String parentUID) {
        parentUIDs.add(parentUID);
    }

    /**
     * @return string of all information in the object
     */
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
