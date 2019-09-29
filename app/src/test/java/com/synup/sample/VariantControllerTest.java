package com.synup.sample;

import com.synup.sample.application.BaseModel;
import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.model.variantcontroller.VariantControl;
import com.synup.sample.model.variantservice.APIServiceProvider;
import com.synup.sample.util.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.List;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({APIServiceProvider.class})
public class VariantControllerTest {


    @Mock
    private VariantControl presenter;

    @Mock
    APIServiceProvider apiServiceProvide;


    private BaseModel.IEventListener mockBaseModelIEventListener;

    @Before
    public void setUp(){
        Whitebox.setInternalState(APIServiceProvider.class, "s_instance", apiServiceProvide);
        VariantControl.createVariantControl();
        MockitoAnnotations.initMocks(this);
        mockBaseModelIEventListener = PowerMockito.mock(BaseModel.IEventListener.class);
    }

    @Test
    public void registerEventListener(){
        VariantControl.createVariantControl().addEventListener(mockBaseModelIEventListener);
    }

    @Test
    public void testAPIGetDataResponse(){
        List<VariantGroup> variantData = mock(List.class);
        List<List<ExcludeList>> excludeList = mock(List.class);
        presenter.onApiGetDataResponse(Constants.RESULT_FAILURE,variantData,excludeList);
        Mockito.verify( presenter,times(1)).onApiGetDataResponse(Constants.RESULT_FAILURE,variantData,excludeList);
    }

    @Test
    public void testGetData(){
        presenter.getData();
        Mockito.verify( presenter,times(1)).getData();
    }
}
