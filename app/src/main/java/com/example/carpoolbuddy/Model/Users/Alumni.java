package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

/**
 * Child of User
 * @author Rikuto
 * @version 0.1
 */
public class Alumni extends User {
    private String graduateYear;

    public Alumni() {
        super();
    }

    public Alumni(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String graduateYear) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.graduateYear = graduateYear;
    }

    /**
     * @return graduate year
     */
    public String getGraduateYear() {
        return graduateYear;
    }

    /**
     * @param graduateYear sets graduate year to parameter
     */
    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    /**
     * @return string of all the information in the object
     */
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
