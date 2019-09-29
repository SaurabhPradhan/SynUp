package com.synup.sample.model.variantservice.callbacks;

import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;

import java.util.List;

public interface IApiResponseCallback {

    void onApiGetDataResponse(int iResult, List<VariantGroup> variantGroupData, List<List<ExcludeList>> excludeData);

}
