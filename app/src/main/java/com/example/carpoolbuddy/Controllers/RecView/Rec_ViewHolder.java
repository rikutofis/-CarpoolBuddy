package com.example.carpoolbuddy.Controllers.RecView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddy.R;


public class Rec_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Rec_Adapter.OnViewClickListner onViewClickListner;

    private TextView txtModel;
    private TextView txtVehicleType;
    private TextView txtCapacity;
    private TextView txtBasePrice;

    public Rec_ViewHolder(@NonNull View itemView, Rec_Adapter.OnViewClickListner onViewClickListner) {
        super(itemView);
        this.onViewClickListner = onViewClickListner;

        txtModel = itemView.findViewById(R.id.RecView_txtVehicleType);
        txtVehicleType = itemView.findViewById(R.id.RecView_txtModel);
        txtCapacity = itemView.findViewById(R.id.RecView_txtCapacity);
        txtBasePrice = itemView.findViewById(R.id.RecView_txtBasePrice);

        itemView.setOnClickListener(this);
    }

    public String getTxtModel() {
        return txtModel.getText().toString();
    }

    public void setTxtModel(String text) {
        txtModel.setText(text);
    }

    public String getTxtVehicleType() {
        return txtVehicleType.getText().toString();
    }

    public void setTxtVehicleType(String text) {
        txtVehicleType.setText(text);
    }

    public String getTxtCapacity() {
        return txtCapacity.getText().toString();
    }

    public void setTxtCapacity(String text) {
        txtCapacity.setText(text);
    }

    public TextView getTxtBasePrice() {
        return txtBasePrice;
    }

    public void setTxtBasePrice(String text) {
        txtBasePrice.setText(text);
    }

    @Override
    public void onClick(View v) {
        onViewClickListner.onViewClick(getAdapterPosition());
    }
}
