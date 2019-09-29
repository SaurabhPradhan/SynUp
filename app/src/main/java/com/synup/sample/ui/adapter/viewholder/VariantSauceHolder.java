package com.synup.sample.ui.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;

public class VariantSauceHolder extends RecyclerView.ViewHolder {


    public Button sauceTypeOne, sauceTypeTwo, sauceTypeThree;
    public TextView sauceDetails;

    public VariantSauceHolder(@NonNull View itemView) {
        super(itemView);
        sauceTypeOne = itemView.findViewById(R.id.item_sauce_one);
        sauceTypeTwo = itemView.findViewById(R.id.item_sauce_two);
        sauceTypeThree = itemView.findViewById(R.id.item_sauce_three);
        sauceDetails = itemView.findViewById(R.id.category_sauce_text);
    }
}
