package com.synup.sample.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.ui.adapter.viewholder.VariantCrustHolder;
import com.synup.sample.ui.adapter.viewholder.VariantSauceHolder;
import com.synup.sample.ui.adapter.viewholder.VariantSizeHolder;

import java.util.List;
import java.util.Locale;

public class VariantCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int CRUST_VIEW_LAYOUT = 1;
    private static final int SIZE_VIEW_LAYOUT = 2;
    private static final int SAUCE_VIEW_LAYOUT = 3;

    private List<VariantGroup> variantGroupList;
    private RecyclerView.ViewHolder viewHolder;

    public VariantCategoryAdapter(List<VariantGroup> variantGroupListItems){
        variantGroupList = variantGroupListItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == CRUST_VIEW_LAYOUT) {
            View variant_crust_viewHolder = inflater.inflate(R.layout.variant_crust_category_layout, viewGroup, false);
            viewHolder = new VariantCrustHolder(variant_crust_viewHolder);
        }else if(viewType == SIZE_VIEW_LAYOUT){
            View variant_size_viewHolder = inflater.inflate(R.layout.variant_size_category_layout, viewGroup, false);
            viewHolder = new VariantSizeHolder(variant_size_viewHolder);
        }else if(viewType == SAUCE_VIEW_LAYOUT){
            View variant_sauce_viewHolder = inflater.inflate(R.layout.variant_sauce_category_layout, viewGroup, false);
            viewHolder = new VariantSauceHolder(variant_sauce_viewHolder);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == CRUST_VIEW_LAYOUT) {
            VariantCrustHolder variantcrustHolder = (VariantCrustHolder) viewHolder;
            configure_crust_variant_viewHolder(variantcrustHolder, position);
        }else if(viewHolder.getItemViewType() == SIZE_VIEW_LAYOUT){
            VariantSizeHolder variantsizeHolder = (VariantSizeHolder) viewHolder;
            configure_size_variant_viewHolder(variantsizeHolder, position);
        }else if(viewHolder.getItemViewType() == SAUCE_VIEW_LAYOUT){
            VariantSauceHolder variantsauceHolder = (VariantSauceHolder) viewHolder;
            configure_sauce_variant_viewHolder(variantsauceHolder, position);
        }

    }

    private void configure_crust_variant_viewHolder(final VariantCrustHolder variantcrustHolder, int position) {
        VariantGroup group = variantGroupList.get(position);
        variantcrustHolder.crustDetails.setText(String.format("Select %s", group.name));
        variantcrustHolder.crustTypeOne.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(0).name, group.variations.get(0).price, group.variations.get(0).inStock));
        variantcrustHolder.crustTypeTwo.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(1).name, group.variations.get(1).price, group.variations.get(1).inStock));
        variantcrustHolder.crustTypeThree.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(2).name, group.variations.get(2).price, group.variations.get(2).inStock));
        variantcrustHolder.crustTypeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCrustLayoutProperty(variantcrustHolder,true,false,false);
            }
        });

        variantcrustHolder.crustTypeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCrustLayoutProperty(variantcrustHolder,false,true,false);
            }
        });

        variantcrustHolder.crustTypeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCrustLayoutProperty(variantcrustHolder,false,false,true);
            }
        });
    }

    private void setCrustLayoutProperty(VariantCrustHolder variantcrustHolder,boolean isFirstCrustSelected,
                                        boolean isSecondCrustSelected, boolean isThirdCrustSelected ){
        variantcrustHolder.crustTypeOne.setSelected(isFirstCrustSelected);
        variantcrustHolder.crustTypeTwo.setSelected(isSecondCrustSelected);
        variantcrustHolder.crustTypeThree.setSelected(isThirdCrustSelected);
    }

    private void configure_size_variant_viewHolder(final VariantSizeHolder variantsizeHolder, int position) {
        VariantGroup group = variantGroupList.get(position);
        variantsizeHolder.sizeDetails.setText(String.format("Select %s", group.name));
        variantsizeHolder.sizeTypeOne.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(0).name, group.variations.get(0).price, group.variations.get(0).inStock));
        variantsizeHolder.sizeTypeTwo.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(1).name, group.variations.get(1).price, group.variations.get(1).inStock));
        variantsizeHolder.sizeTypeThree.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(2).name, group.variations.get(2).price, group.variations.get(2).inStock));
        variantsizeHolder.sizeTypeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSizeLayoutProperty(variantsizeHolder,true,false,false);
            }
        });

        variantsizeHolder.sizeTypeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSizeLayoutProperty(variantsizeHolder,false,true,false);
            }
        });

        variantsizeHolder.sizeTypeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSizeLayoutProperty(variantsizeHolder,false,false,true);
            }
        });
    }

    private void setSizeLayoutProperty(VariantSizeHolder variantsizeHolder,boolean isFirstSizeSelected,
                                       boolean isSecondSizeSelected, boolean isThirdSizeSelected ){
        variantsizeHolder.sizeTypeOne.setSelected(isFirstSizeSelected);
        variantsizeHolder.sizeTypeTwo.setSelected(isSecondSizeSelected);
        variantsizeHolder.sizeTypeThree.setSelected(isThirdSizeSelected);
    }

    private void configure_sauce_variant_viewHolder(final VariantSauceHolder variantsauceHolder, int position) {
        VariantGroup group = variantGroupList.get(position);
        variantsauceHolder.sauceDetails.setText(String.format("Select %s", group.name));
        variantsauceHolder.sauceTypeOne.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(0).name, group.variations.get(0).price, group.variations.get(0).inStock));
        variantsauceHolder.sauceTypeTwo.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(1).name, group.variations.get(1).price, group.variations.get(1).inStock));
        variantsauceHolder.sauceTypeThree.setText(String.format(Locale.getDefault(),"%s\n\nPrice:%d\nStock:%d", group.variations.get(2).name, group.variations.get(2).price, group.variations.get(2).inStock));
        variantsauceHolder.sauceTypeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSauceLayoutProperty(variantsauceHolder,true,false,false);
            }

        });

        variantsauceHolder.sauceTypeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSauceLayoutProperty(variantsauceHolder,false,true,false);
            }
        });

        variantsauceHolder.sauceTypeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSauceLayoutProperty(variantsauceHolder,false,false,true);
            }
        });
    }

    private void setSauceLayoutProperty(VariantSauceHolder variantsauceHolder,boolean isFirstSauceSelected,
                                        boolean isSecondSauceSelected, boolean isThirdSauceSelected ){
        variantsauceHolder.sauceTypeOne.setSelected(isFirstSauceSelected);
        variantsauceHolder.sauceTypeTwo.setSelected(isSecondSauceSelected);
        variantsauceHolder.sauceTypeThree.setSelected(isThirdSauceSelected);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return CRUST_VIEW_LAYOUT;
        }else if(position == 1){
            return SIZE_VIEW_LAYOUT;
        }else if(position == 2){
            return SAUCE_VIEW_LAYOUT;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return variantGroupList.size();
    }


}
