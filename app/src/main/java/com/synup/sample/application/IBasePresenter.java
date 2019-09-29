package com.synup.sample.application;

import android.os.Bundle;

public interface IBasePresenter<T> {

    BaseModel getModel();

    void onCreate(Bundle savedInstanceState);

    void onDestroy();

    void setView(T view);

    void onButtonClick();
}