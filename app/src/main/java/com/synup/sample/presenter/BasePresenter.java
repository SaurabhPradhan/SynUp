package com.synup.sample.presenter;

import android.os.Bundle;

import com.synup.sample.application.BaseModel;
import com.synup.sample.application.IBasePresenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

//This class is used to make generic Presenter where any view instance can be saved

public  class BasePresenter<T> implements IBasePresenter<T> {

    private Reference<T> mViewRef;

    @Override
    public BaseModel getModel() {
        return null;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
    }

    @Override
    public void onButtonClick(){}

    @Override
    public void onDestroy() {
        setView( null );
    }

    @Override
    public void setView( T view ) {
        if(view != null) {
            mViewRef = new WeakReference<>(view);
        } else if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public T getView(){
        if (mViewRef != null) {
            return mViewRef.get();
        }
        return null;
    }
}
