package com.synup.sample.ui.view;

import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;

import java.util.List;

//Here we are exporting interface to presenter which presenter will use to manipulate view i.e fragments

public interface IDataView {

    void onDataDownloadResponse(int eventId, List<VariantGroup> variantGroupData, List<List<ExcludeList>> excludeData);
}
