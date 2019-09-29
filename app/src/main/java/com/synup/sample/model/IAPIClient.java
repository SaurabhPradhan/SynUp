package com.synup.sample.model;

import com.synup.sample.bean.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIClient {

    @GET("/bins/19u0sf")
    Call<Root> doGetRootVariant();
}
