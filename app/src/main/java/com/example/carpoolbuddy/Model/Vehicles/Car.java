package com.example.carpoolbuddy.Model.Vehicles;

import android.os.Parcel;

import java.util.ArrayList;

/**
 * Child of Vehicle
 * @author Rikuto
 * @version 0.1
 */
public class Car extends Vehicle {
    private int range;

    public Car(){
        super();
    }

    public Car(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String imageID, int range) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, imageID);
        this.range = range;
    }

    public Car(Parcel in) {
        super(in);
        range = in.readInt();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    /**
     * @return range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range sets range to parameter
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return string of all information in the object
     */
    @Override
    public String toString() {
        return "Car{" +
                "owner='" + getOwner() + '\'' +
                ", model='" + getModel() + '\'' +
                ", capacity=" + getOwner() +
                ", vehicleID='" + getVehicleID() + '\'' +
                ", ridersUIDs=" + getRidersUIDs() +
                ", open=" + isOpen() +
                ", vehicleType='" + getVehicleType() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", range=" + range +
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
        dest.writeInt(range);
    }
}
