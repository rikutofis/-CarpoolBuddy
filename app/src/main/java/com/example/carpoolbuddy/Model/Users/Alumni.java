package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

public class Alumni extends User {
    private String graduateYear;

    public Alumni() {
        super();
    }

    public Alumni(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String graduateYear) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.graduateYear = graduateYear;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "userID='" + getUserID() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", userType='" + getUserType() + '\'' +
                ", priceMultiplier=" + getPriceMultiplier() +
                ", ownedVehicles=" + getOwnedVehicles() +
                ", graduateYear=" + graduateYear +
                '}';
    }
}
