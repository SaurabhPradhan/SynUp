package com.synup.sample.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;
import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.bean.Variation;
import com.synup.sample.ui.adapter.viewholder.VariantCombinationHolder;
import java.util.List;

public class VariantCombinationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<VariantGroup> variantGroupList;
    private List<List<ExcludeList>> excludeList;
    private static final int COMBINATION_VIEW_LAYOUT = 1;
    private RecyclerView.ViewHolder viewHolder;

    public VariantCombinationAdapter(List<List<ExcludeList>> excludeListItems, List<VariantGroup> variantGroupListItems) {
        excludeList = excludeListItems;
        variantGroupList = variantGroupListItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == COMBINATION_VIEW_LAYOUT) {
            View variant_combination_viewHolder = inflater.inflate(R.layout.variant_combination_layout, viewGroup, false);
            viewHolder = new VariantCombinationHolder(variant_combination_viewHolder);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == COMBINATION_VIEW_LAYOUT) {
            VariantCombinationHolder variantcombinationHolder = (VariantCombinationHolder) viewHolder;
            configure_variant_combination_viewHolder(variantcombinationHolder, position);
        }
    }

    private void configure_variant_combination_viewHolder(VariantCombinationHolder variantcombinationHolder, int position) {
        manipulateDataItem(variantcombinationHolder,position);
    }

    private void manipulateDataItem(VariantCombinationHolder variantcombinationHolder, int position){
        StringBuilder groupName = new StringBuilder();
        for(int j = 0; j< excludeList.get(position).size(); j++) {
            String excludeGroupId = excludeList.get(position).get(j).groupId;
            String excludeVariationId = excludeList.get(position).get(j).variationId;

            for(int k = 0; k< variantGroupList.size(); k++){
                VariantGroup groupData = variantGroupList.get(k);
                if(excludeGroupId.equals(groupData.groupId)){
                    groupName.append(groupData.name).append(":");
                    for(int l =0;l<groupData.variations.size();l++){
                        Variation variationData = groupData.variations.get(l);
                        if (variationData.id.equals(excludeVariationId)) {
                            groupName.append(variationData.name).append("\n\n");
                            break;
                        }
                    }
                    break;
                }
            }
            variantcombinationHolder.variantCombination.setText(groupName);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return COMBINATION_VIEW_LAYOUT;
    }

    @Override
    public int getItemCount() {
        return excludeList.size();
    }
}
