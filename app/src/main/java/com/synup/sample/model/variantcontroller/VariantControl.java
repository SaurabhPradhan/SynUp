package com.synup.sample.model.variantcontroller;


import com.synup.sample.application.BaseModel;
import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.model.variantservice.APIServiceProvider;
import com.synup.sample.model.variantservice.callbacks.IApiResponseCallback;

import java.util.ArrayList;
import java.util.List;

// This model class will fetch the data from the service provider
// update the data and sent to presenter to update the view

public class VariantControl implements IApiResponseCallback {

    private static VariantControl s_instance;
    private final APIServiceProvider apiServiceProvide;

    private List<BaseModel.IEventListener> m_aryListener = new ArrayList<>();


    @SuppressWarnings("UnusedReturnValue")
    public static VariantControl createVariantControl() {
        if ( s_instance == null ) {
            synchronized ( VariantControl.class ) {
                if ( s_instance == null ) {
                    s_instance = new VariantControl( );
                }
            }
        }
        return s_instance;
    }

    private VariantControl( ) {
        apiServiceProvide = APIServiceProvider.getInstance();
        apiServiceProvide.registerApiResponseCallback(this);
    }

    public static VariantControl getInstance() {
        return s_instance;
    }

    public void addEventListener( BaseModel.IEventListener listener ) {
        m_aryListener.add( listener );
    }

    public void removeEventListener( BaseModel.IEventListener listener ) {
        m_aryListener.remove( listener );
    }

    public void unregisterApiResponseCallback(){
        apiServiceProvide.unregisterApiResponseCallback(this);
    }

    private void dispatchEvent(int iEventID,List<VariantGroup> variantGroupData, List<List<ExcludeList>> excludeData) {
        for ( BaseModel.IEventListener listener : m_aryListener )
            listener.onHandleEvent(iEventID, variantGroupData,excludeData);
    }

    private void sendData( int resultType, List<VariantGroup> variantGroupData, List<List<ExcludeList>> excludeData ) {
        dispatchEvent(resultType, variantGroupData,excludeData);
    }

    @Override
    public void onApiGetDataResponse(int iResult, List<VariantGroup> variantGroupData, List<List<ExcludeList>> excludeData) {
        sendData(iResult,variantGroupData,excludeData);
    }

    public void getData(){
        apiServiceProvide.requestData();
    }
}
