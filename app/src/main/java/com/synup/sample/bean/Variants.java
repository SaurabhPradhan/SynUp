package com.synup.sample.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Variants {

    @SerializedName("variant_groups")
    @Expose
    public List<VariantGroup> variantGroups = null;
    @SerializedName("exclude_list")
    @Expose
    public List<List<ExcludeList>> excludeList = null;
}
