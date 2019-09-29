package com.synup.sample.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcludeList {

    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("variation_id")
    @Expose
    public String variationId;
}
