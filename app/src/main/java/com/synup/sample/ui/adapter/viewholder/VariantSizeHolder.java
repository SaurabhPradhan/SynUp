package com.synup.sample.ui.adapter.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;

public class VariantSizeHolder extends RecyclerView.ViewHolder {


    public Button sizeTypeOne, sizeTypeTwo, sizeTypeThree;
    public TextView sizeDetails;

    public VariantSizeHolder(@NonNull View itemView) {
        super(itemView);
        sizeTypeOne = itemView.findViewById(R.id.item_size_one);
        sizeTypeTwo = itemView.findViewById(R.id.item_size_two);
        sizeTypeThree = itemView.findViewById(R.id.item_size_three);
        sizeDetails = itemView.findViewById(R.id.category_size_text);
    }
}

