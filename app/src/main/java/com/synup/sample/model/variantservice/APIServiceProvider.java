package com.synup.sample.model.variantservice;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;

import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.Root;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.model.APIClientModel;
import com.synup.sample.model.IAPIClient;
import com.synup.sample.model.variantservice.callbacks.IApiResponseCallback;
import com.synup.sample.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Class interact with retrofit module to get the data from the server
//and send the data to model

public class APIServiceProvider {

    @SuppressLint("StaticFieldLeak")
    private static APIServiceProvider s_instance;
    private final APICallbackHandler apicallbackHandler;
    private final Context appContext;

    @SuppressWarnings("UnusedReturnValue")
    public static APIServiceProvider createAPIServiceProvider(Context context) {
        if (s_instance == null) {
            synchronized (APIServiceProvider.class) {
                if (s_instance == null) {
                    s_instance = new APIServiceProvider(context);
                }
            }
        }
        return s_instance;
    }

    public static APIServiceProvider getInstance() {
        return s_instance;
    }

    private APIServiceProvider(Context context){
        appContext = context;
        apicallbackHandler = new APICallbackHandler();
        requestData();
    }

    public void registerApiResponseCallback(@NonNull IApiResponseCallback callback) {
        apicallbackHandler.registerApiResponseCallback(callback);
    }

    public void unregisterApiResponseCallback(@NonNull IApiResponseCallback callback) {
        apicallbackHandler.unregisterApiResponseCallback(callback);
    }

    public void requestData(){
        IAPIClient apiClientInterface = APIClientModel.getClient(appContext).create(IAPIClient.class);
        Call<Root> call = apiClientInterface.doGetRootVariant();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                Root resource = response.body();
                List<VariantGroup> variantGroupData = resource.variants.variantGroups;
                List<List<ExcludeList>> excludeLists = resource.variants.excludeList;
                apicallbackHandler.onApiGetDataResponse(Constants.RESULT_SUCCESS,variantGroupData,excludeLists);
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                apicallbackHandler.onApiGetDataResponse(Constants.RESULT_FAILURE,null,null);
            }
        });
    }
}
