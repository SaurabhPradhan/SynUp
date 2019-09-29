package com.synup.sample.model;

import android.content.Context;

import com.synup.sample.util.Helper;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Class to build the retrofit object
public final class APIClientModel {

    private APIClientModel(){}
    public static Retrofit getClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(Objects.requireNonNull(Helper.getConfigValue(context, "api_url")))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
