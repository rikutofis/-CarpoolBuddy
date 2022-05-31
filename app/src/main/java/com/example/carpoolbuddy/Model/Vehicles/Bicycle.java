package com.example.carpoolbuddy.Model.Vehicles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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

    public String getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(bicycleType);
        dest.writeInt(weight);
        dest.writeInt(weightCapacity);
    }
}
