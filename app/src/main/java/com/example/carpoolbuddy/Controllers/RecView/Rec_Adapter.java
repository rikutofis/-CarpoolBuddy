package com.example.carpoolbuddy.Controllers.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.Model.Vehicles.*;
import com.example.carpoolbuddy.R;

import java.util.ArrayList;

public class Rec_Adapter extends RecyclerView.Adapter<Rec_ViewHolder> {

    private ArrayList<Vehicle> vehicleList;
    private OnViewClickListner onViewClickListner;

    public Rec_Adapter(ArrayList<Vehicle> vehicleList, OnViewClickListner onViewClickListner) {
        this.vehicleList = vehicleList;
        this.onViewClickListner = onViewClickListner;
    }

    @NonNull
    @Override
    public Rec_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view, parent, false);

        Rec_ViewHolder holder = new Rec_ViewHolder(myView, onViewClickListner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Rec_ViewHolder holder, int position) {
        holder.setTxtModel(vehicleList.get(position).getModel());
        holder.setTxtVehicleType(vehicleList.get(position).getVehicleType());
        holder.setTxtCapacity("Capacity: "+vehicleList.get(position).getCapacity());
        holder.setTxtBasePrice("€"+vehicleList.get(position).getBasePrice());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public interface OnViewClickListner {
        public void onViewClick(int position);
    }
}
