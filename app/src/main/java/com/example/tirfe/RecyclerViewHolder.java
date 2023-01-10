package com.example.tirfe;

import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * view holder class storing item elements of the recycler view
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public Button cardButton;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        cardButton = itemView.findViewById(R.id.cardamt_Button);



    }
}
