package com.synup.sample.ui.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;

public class VariantCrustHolder extends RecyclerView.ViewHolder {


    public Button crustTypeOne, crustTypeTwo, crustTypeThree;
    public TextView crustDetails;

    public VariantCrustHolder(@NonNull View itemView) {
        super(itemView);
        crustTypeOne = itemView.findViewById(R.id.item_crust_one);
        crustTypeTwo = itemView.findViewById(R.id.item_crust_two);
        crustTypeThree = itemView.findViewById(R.id.item_crust_three);
        crustDetails = itemView.findViewById(R.id.category_crust_text);
    }
}
