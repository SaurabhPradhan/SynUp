package com.synup.sample.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VariantGroup {

    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("variations")
    @Expose
    public List<Variation> variations = null;
}
