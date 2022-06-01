package com.example.carpoolbuddy.Controllers.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.Model.Vehicles.*;
import com.example.carpoolbuddy.R;

import java.util.ArrayList;

/**
 * Recyclerview Adapter
 * @author Rikuto
 * @version 0.1
 */
public class Rec_Adapter extends RecyclerView.Adapter<Rec_ViewHolder> {

    private ArrayList<Vehicle> vehicleList;
    private OnViewClickListener onViewClickListener;

    public Rec_Adapter(ArrayList<Vehicle> vehicleList, OnViewClickListener onViewClickListener) {
        this.vehicleList = vehicleList;
        this.onViewClickListener = onViewClickListener;
    }

    /**
     * creates new viewholder
     * @param parent
     * @param viewType
     * @return created viewholder
     */
    @NonNull
    @Override
    public Rec_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view, parent, false);

        Rec_ViewHolder holder = new Rec_ViewHolder(myView, onViewClickListener);
        return holder;
    }

    /**
     * sets all the information to the viewholder
     * @param holder viewholder
     * @param position selected position in the recycler view
     */
    @Override
    public void onBindViewHolder(@NonNull Rec_ViewHolder holder, int position) {
        holder.setTxtModel(vehicleList.get(position).getModel());
        holder.setTxtVehicleType(vehicleList.get(position).getVehicleType());
        holder.setTxtCapacity("Capacity: "+vehicleList.get(position).getCapacity());
        holder.setTxtBasePrice("€"+vehicleList.get(position).getBasePrice());
    }

    /**
     * @return number of vehicles
     */
    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    /**
     * interface of OnViewClickListener
     */
    public interface OnViewClickListener {
        public void onViewClick(int position);
    }
}
