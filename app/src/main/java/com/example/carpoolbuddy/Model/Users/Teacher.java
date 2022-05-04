package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

public class Teacher extends User {
    private String inSchoolTitle;

    public Teacher() {
        super();
    }

    public Teacher(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String inSchoolTitle) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.inSchoolTitle = inSchoolTitle;
    }

    public String getInSchoolTitle() {
        return inSchoolTitle;
    }

    public void setInSchoolTitle(String inSchoolTitle) {
        this.inSchoolTitle = inSchoolTitle;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userID='" + getUserID() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", userType='" + getUserType() + '\'' +
                ", priceMultiplier=" + getPriceMultiplier() +
                ", ownedVehicles=" + getOwnedVehicles() +
                ", inSchoolTitle='" + inSchoolTitle + '\'' +
                '}';
    }
}
