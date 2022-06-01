package com.example.carpoolbuddy.Model.Vehicles;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Parent of all vehicles
 * @author Rikuto
 * @version 0.1
 */
public class Vehicle implements Serializable, Parcelable {
    private String owner;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;
    private boolean open;
    private String vehicleType;
    private double basePrice;

    private String imageID;

    public Vehicle(){}

    public Vehicle(String owner, String model, int capacity, String vehicleID, ArrayList<String> ridersUIDs, boolean open, String vehicleType, double basePrice, String imageID) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.ridersUIDs = ridersUIDs;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
        this.imageID = imageID;
    }

    protected Vehicle(Parcel in) {
        owner = in.readString();
        model = in.readString();
        capacity = in.readInt();
        vehicleID = in.readString();
        ridersUIDs = in.createStringArrayList();
        open = in.readByte() != 0;
        vehicleType = in.readString();
        basePrice = in.readDouble();
        imageID = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    /**
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner sets owner to parameter
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model sets model to parameter
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity sets capacity to parameter
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return vehicleID
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * @param vehicleID sets vehicleID to parameter
     */
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * @return riderUIDs
     */
    public ArrayList<String> getRidersUIDs() {
        return ridersUIDs;
    }

    /**
     * @param riderUID adds parameter to riderUIDs
     */
    public void addRidersUID(String riderUID) {
        ridersUIDs.add(riderUID);
    }

    /**
     * @param ridersUIDs sets riderUIDs to parameter
     */
    public void setRidersUIDs(ArrayList<String> ridersUIDs) {
        this.ridersUIDs = ridersUIDs;
    }

    /**
     * @return open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @param open sets open to parameter
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * @return vehicle type
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType sets vehicle type to parameter
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return base price
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * @param basePrice sets base price to parameter
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return imageID
     */
    public String getImageID() {
        return imageID;
    }

    /**
     * @param imageID sets imageID to parameter
     */
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    /**
     * @return string of all information in the object
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "owner='" + owner + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vehicleID='" + vehicleID + '\'' +
                ", ridersUIDs=" + ridersUIDs +
                ", open=" + open +
                ", vehicleType='" + vehicleType + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(owner);
        dest.writeString(model);
        dest.writeInt(capacity);
        dest.writeString(vehicleID);
        dest.writeStringList(ridersUIDs);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(vehicleType);
        dest.writeDouble(basePrice);
        dest.writeString(imageID);
    }
}
