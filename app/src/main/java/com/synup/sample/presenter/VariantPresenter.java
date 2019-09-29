package com.synup.sample.presenter;

import android.os.Bundle;

import com.synup.sample.application.BaseModel;
import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.model.variantcontroller.VariantControl;
import com.synup.sample.ui.view.IDataView;

import java.util.List;
import java.util.Objects;

//This presenter class will trigger the business logic and tells the view when to update view using onHandleEvent().
//It therefore interacts with the model class Variant Control and fetches and transforms data from the model to update the view.

public class VariantPresenter extends BasePresenter<IDataView> implements BaseModel.IEventListener  {


    private final VariantControl variantController;

    public VariantPresenter() {
        variantController = VariantControl.getInstance();
        addEventListener();
    }

    @Override
    public BaseModel getModel() {
        return super.getModel();
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public void onButtonClick() {
        super.onButtonClick();
        variantController.getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeEventListener();
        variantController.unregisterApiResponseCallback();
    }

    @Override
    public void setView( IDataView view ) {
        super.setView( view );
    }

    @Override
    public IDataView getView() {
        return super.getView();
    }

    private void addEventListener() {
        Objects.requireNonNull(variantController).addEventListener(this);
    }

    private void removeEventListener() {
        variantController.removeEventListener( this );
    }


    @Override
    public void onHandleEvent(int iEventID, List<VariantGroup> data, List<List<ExcludeList>> excludeData) {
        IDataView view = getView();
        if ( view != null ) {
            view.onDataDownloadResponse(iEventID,data,excludeData);
        }
    }
}
