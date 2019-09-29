package com.synup.sample;


import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.model.variantcontroller.VariantControl;
import com.synup.sample.presenter.VariantPresenter;
import com.synup.sample.ui.view.IDataView;
import com.synup.sample.util.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VariantControl.class})
public class VariantPresenterTest {

    @Mock
    private VariantPresenter presenter;

    @Mock
    VariantControl variantControl;

    private IDataView view;

    @Before
    public void setUp() {
        Whitebox.setInternalState(VariantControl.class, "s_instance", variantControl);
        MockitoAnnotations.initMocks(this);
        view = mock(IDataView.class);
        presenter = Mockito.spy(new VariantPresenter());
        Mockito.doReturn(view).when(presenter).getView();
    }

    @Test
    public void testOnHandleEventWhenResponseIsSuccess(){
        List<VariantGroup> variantData = mock(List.class);
        List<List<ExcludeList>> excludeList = mock(List.class);
        presenter.onHandleEvent(Constants.RESULT_SUCCESS,variantData,excludeList);
        Mockito.verify(view,times(1)).onDataDownloadResponse(Constants.RESULT_SUCCESS,variantData,excludeList);
    }

    @Test
    public void testOnHandleEventWhenResponseIsFailed(){
        presenter.onHandleEvent(Constants.RESULT_FAILURE,null,null);
        Mockito.verify(view,times(1)).onDataDownloadResponse(Constants.RESULT_FAILURE,null,null);
    }
}
