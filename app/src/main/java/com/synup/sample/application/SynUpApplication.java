package com.synup.sample.application;

import android.app.Application;
import com.synup.sample.model.variantcontroller.VariantControl;
import com.synup.sample.model.variantservice.APIServiceProvider;


public class SynUpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initManager();
    }

    //Create the single instance for model classes
    private void initManager() {
        APIServiceProvider.createAPIServiceProvider(getApplicationContext());
        VariantControl.createVariantControl();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
