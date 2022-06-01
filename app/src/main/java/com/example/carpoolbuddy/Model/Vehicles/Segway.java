package com.example.carpoolbuddy.Model.Vehicles;

import android.os.Parcel;

import java.util.ArrayList;

/**
 * Child of Vehicle
 * @author Rikuto
 * @version 0.1
 */
public class Segway extends Vehicle {
    private int range;
    private int weightCapacity;

    public Segway(){
        super();
    }

    public Segway(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String imageID, int range, int weightCapacity) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, imageID);
        this.range = range;
        this.weightCapacity = weightCapacity;
    }

    public Segway(Parcel in) {
        super(in);
        range = in.readInt();
        weightCapacity = in.readInt();
    }

    public static final Creator<Segway> CREATOR = new Creator<Segway>() {
        @Override
        public Segway createFromParcel(Parcel in) {
            return new Segway(in);
        }

        @Override
        public Segway[] newArray(int size) {
            return new Segway[size];
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
     * @return weight capacity
     */
    public int getWeightCapacity() {
        return weightCapacity;
    }

    /**
     * @param weightCapacity sets weight capacity to parameter
     */
    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    /**
     * @return string of all information in the object
     */
    @Override
    public String toString() {
        return "Segway{" +
                "owner='" + getOwner() + '\'' +
                ", model='" + getModel() + '\'' +
                ", capacity=" + getOwner() +
                ", vehicleID='" + getVehicleID() + '\'' +
                ", ridersUIDs=" + getRidersUIDs() +
                ", open=" + isOpen() +
                ", vehicleType='" + getVehicleType() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", range=" + range +
                ", weightCapacity=" + weightCapacity +
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
        dest.writeInt(weightCapacity);
    }
}
