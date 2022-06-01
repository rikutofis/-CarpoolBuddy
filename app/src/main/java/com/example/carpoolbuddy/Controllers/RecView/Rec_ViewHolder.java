package com.example.carpoolbuddy.Controllers.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.R;

/**
 * Recyclerview View Holder
 * @author Rikuto
 * @version 0.1
 */
public class Rec_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Rec_Adapter.OnViewClickListener onViewClickListener;

    private TextView txtModel;
    private TextView txtVehicleType;
    private TextView txtCapacity;
    private TextView txtBasePrice;

    public Rec_ViewHolder(@NonNull View itemView, Rec_Adapter.OnViewClickListener onViewClickListener) {
        super(itemView);
        this.onViewClickListener = onViewClickListener;

        txtModel = itemView.findViewById(R.id.RecView_txtVehicleType);
        txtVehicleType = itemView.findViewById(R.id.RecView_txtModel);
        txtCapacity = itemView.findViewById(R.id.RecView_txtCapacity);
        txtBasePrice = itemView.findViewById(R.id.RecView_txtBasePrice);

        itemView.setOnClickListener(this);
    }

    /**
     * @return model
     */
    public String getTxtModel() {
        return txtModel.getText().toString();
    }

    /**
     * @param text sets model to parameter
     */
    public void setTxtModel(String text) {
        txtModel.setText(text);
    }

    /**
     * @return vehicle type
     */
    public String getTxtVehicleType() {
        return txtVehicleType.getText().toString();
    }

    /**
     * @param text sets vehicle type to parameter
     */
    public void setTxtVehicleType(String text) {
        txtVehicleType.setText(text);
    }

    /**
     * @return capacity
     */
    public String getTxtCapacity() {
        return txtCapacity.getText().toString();
    }

    /**
     * @param text sets capacity to parameter
     */
    public void setTxtCapacity(String text) {
        txtCapacity.setText(text);
    }

    /**
     * @return base price
     */
    public TextView getTxtBasePrice() {
        return txtBasePrice;
    }

    /**
     * @param text sets base price to parameter
     */
    public void setTxtBasePrice(String text) {
        txtBasePrice.setText(text);
    }

    /**
     * invokes onViewClick on the adapter position
     * @param v
     */
    @Override
    public void onClick(View v) {
        onViewClickListener.onViewClick(getAdapterPosition());
    }
}
