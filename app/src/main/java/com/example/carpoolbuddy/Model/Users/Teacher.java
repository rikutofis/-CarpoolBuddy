package com.example.carpoolbuddy.Model.Users;

import java.util.ArrayList;

/**
 * Child of User
 * @author Rikuto
 * @version 0.1
 */
public class Teacher extends User {
    private String inSchoolTitle;

    public Teacher() {
        super();
    }

    public Teacher(String userID, String name, String email, String userType, double priceMultiplier, ArrayList<String> ownedVehicles, String inSchoolTitle) {
        super(userID, name, email, userType, priceMultiplier, ownedVehicles);
        this.inSchoolTitle = inSchoolTitle;
    }

    /**
     * @return in school title
     */
    public String getInSchoolTitle() {
        return inSchoolTitle;
    }

    /**
     * @param inSchoolTitle sets in school title to parameter
     */
    public void setInSchoolTitle(String inSchoolTitle) {
        this.inSchoolTitle = inSchoolTitle;
    }

    /**
     * @return string of all the information in the object
     */
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
