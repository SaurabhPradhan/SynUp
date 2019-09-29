package com.synup.sample.model.variantservice;

import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.model.variantservice.callbacks.IApiResponseCallback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

public class APICallbackHandler extends Handler implements IApiResponseCallback {

    private static final int MSG_ADD_REMOVE_API_RESPONSE_CALLBACK = 1;
    private ArrayList<IApiResponseCallback> apiResponseCallbacksList = new ArrayList<>();

    APICallbackHandler() {
        super( Looper.getMainLooper() );
    }

    void registerApiResponseCallback(IApiResponseCallback callback) {
        setListening( callback, true );
    }

    void unregisterApiResponseCallback(IApiResponseCallback callback) {
        setListening( callback, false );
    }

    private void setListening( IApiResponseCallback callback, boolean register ) {
        obtainMessage(MSG_ADD_REMOVE_API_RESPONSE_CALLBACK, register ? 1 : 0, 0, callback ).sendToTarget();
    }

    @Override
    public void handleMessage(Message msg) {
        if ( msg.what == MSG_ADD_REMOVE_API_RESPONSE_CALLBACK) {
            if (msg.arg1 != 0) {
                apiResponseCallbacksList.add((IApiResponseCallback) msg.obj);
            } else {
                apiResponseCallbacksList.remove(msg.obj);
            }
        }
    }

    @Override
    public void onApiGetDataResponse(final int iResult, final List<VariantGroup> variantGroupData, final List<List<ExcludeList>> excludeData) {
        post( new Runnable() {
            @Override
            public void run() {
                for ( IApiResponseCallback callback : apiResponseCallbacksList) {
                    callback.onApiGetDataResponse( iResult, variantGroupData,excludeData);
                }
            }
        } );
    }
}
