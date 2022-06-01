package com.example.carpoolbuddy.Model.Vehicles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Child of Vehicle
 * @author Rikuto
 * @version 0.1
 */
public class Bicycle extends Vehicle {
    private String bicycleType;
    private int weight;
    private int weightCapacity;

    public Bicycle(){
        super();
    }

    public Bicycle(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String bicycleType, String imageID, int weight, int weightCapacity) {
        super(owner, model, capacity, vehicleID, ridersUIDs, open, vehicleType, basePrice, imageID);
        this.bicycleType = bicycleType;
        this.weight = weight;
        this.weightCapacity = weightCapacity;
    }

    public Bicycle(Parcel in) {
        super(in);
        bicycleType = in.readString();
        weight = in.readInt();
        weightCapacity = in.readInt();
    }

    public static final Creator<Bicycle> CREATOR = new Creator<Bicycle>() {
        @Override
        public Bicycle createFromParcel(Parcel in) {
            return new Bicycle(in);
        }

        @Override
        public Bicycle[] newArray(int size) {
            return new Bicycle[size];
        }
    };

    /**
     * @return bicycle type
     */
    public String getBicycleType() {
        return bicycleType;
    }

    /**
     * @param bicycleType sets bicycle type to parameter
     */
    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
    }

    /**
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight sets weight to parameter
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return weight capacity
     */
    public int getWeightCapacity() {
        return weightCapacity;
    }

    /**
     * @param weightCapacity sets weigth capacity to parameter
     */
    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    /**
     * @return string of all the information of the object
     */
    @Override
    public String toString() {
        return "Bicycle{" +
                "owner='" + getOwner() + '\'' +
                ", model='" + getModel() + '\'' +
                ", capacity=" + getOwner() +
                ", vehicleID='" + getVehicleID() + '\'' +
                ", ridersUIDs=" + getRidersUIDs() +
                ", open=" + isOpen() +
                ", vehicleType='" + getVehicleType() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", bicycleType='" + bicycleType + '\'' +
                ", weight=" + weight +
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
        dest.writeString(bicycleType);
        dest.writeInt(weight);
        dest.writeInt(weightCapacity);
    }
}
