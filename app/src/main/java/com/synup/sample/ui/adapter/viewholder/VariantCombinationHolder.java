package com.synup.sample.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;

public class VariantCombinationHolder extends RecyclerView.ViewHolder {

    public TextView variantCombination;

    public VariantCombinationHolder(@NonNull View itemView) {
        super(itemView);
        variantCombination = itemView.findViewById(R.id.id_combination_one);
    }
}
