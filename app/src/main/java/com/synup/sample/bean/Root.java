package com.synup.sample.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root {

    @SerializedName("variants")
    @Expose
    public Variants variants;
}
