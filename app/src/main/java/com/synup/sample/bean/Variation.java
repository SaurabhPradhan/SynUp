package com.synup.sample.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variation {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("default")
    @Expose
    public Integer _default;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("inStock")
    @Expose
    public Integer inStock;
    @SerializedName("isVeg")
    @Expose
    public Integer isVeg;

}
