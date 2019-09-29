package com.synup.sample.application;


import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;

import java.util.List;

public class BaseModel {

     public interface IEventListener {
        void onHandleEvent(int iEventID, List<VariantGroup> data, List<List<ExcludeList>> excludeData);
    }

    public BaseModel() {
    }

}
