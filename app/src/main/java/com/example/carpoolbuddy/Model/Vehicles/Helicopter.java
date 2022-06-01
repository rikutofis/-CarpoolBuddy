package com.example.carpoolbuddy.Model.Vehicles;

import android.os.Parcel;

import java.util.ArrayList;

/**
 * Child of Vehicle
 * @author Rikuto
 * @version 0.1
 */
public class Helicopter extends Vehicle {
    private int maxAltitude;
    private int maxAirSpeed;

    public Helicopter(){
        super();
    }

    public Helicopter(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String imageID, int maxAltitude, int maxAirSpeed) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, imageID);
        this.maxAltitude = maxAltitude;
        this.maxAirSpeed = maxAirSpeed;
    }

    public Helicopter(Parcel in) {
        super(in);
        maxAltitude = in.readInt();
        maxAirSpeed = in.readInt();
    }

    public static final Creator<Helicopter> CREATOR = new Creator<Helicopter>() {
        @Override
        public Helicopter createFromParcel(Parcel in) {
            return new Helicopter(in);
        }

        @Override
        public Helicopter[] newArray(int size) {
            return new Helicopter[size];
        }
    };

    /**
     * @return max altitude
     */
    public int getMaxAltitude() {
        return maxAltitude;
    }

    /**
     * @param maxAltitude sets max altitude to parameter
     */
    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    /**
     * @return max air speed
     */
    public int getMaxAirSpeed() {
        return maxAirSpeed;
    }

    /**
     * @param maxAirSpeed sets max air speed to parameter
     */
    public void setMaxAirSpeed(int maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    /**
     * @return string of all the information in the object
     */
    @Override
    public String toString() {
        return "Helicopter{" +
                "owner='" + getOwner() + '\'' +
                ", model='" + getModel() + '\'' +
                ", capacity=" + getOwner() +
                ", vehicleID='" + getVehicleID() + '\'' +
                ", ridersUIDs=" + getRidersUIDs() +
                ", open=" + isOpen() +
                ", vehicleType='" + getVehicleType() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", maxAltitude=" + maxAltitude +
                ", maxAirSpeed=" + maxAirSpeed +
                '}';
    }

    /**
     * writes to parcel
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(maxAltitude);
        dest.writeInt(maxAirSpeed);
    }
}
