package com.synup.sample;

import android.content.Context;
import android.os.Looper;
import com.synup.sample.model.APIClientModel;
import com.synup.sample.model.IAPIClient;
import com.synup.sample.model.variantservice.APICallbackHandler;
import com.synup.sample.model.variantservice.APIServiceProvider;

import com.synup.sample.bean.Root;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Context.class,APICallbackHandler.class, Looper.class,Retrofit.class,APIClientModel.class,IAPIClient.class,Response.class})
@PowerMockIgnore("javax.net.ssl.*")
public class APIServiceProviderTest {

    @Mock
    APIServiceProvider apiServiceProvider;
    private Call<Root> callData;


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(Looper.class);
        Looper mockMainThreadLooper = mock(Looper.class);
        when(Looper.getMainLooper()).thenReturn(mockMainThreadLooper);
        Context context = mock(Context.class);

        PowerMockito.mockStatic(APIClientModel.class);
        Retrofit retrofit = PowerMockito.mock(Retrofit.class);
        PowerMockito.when(APIClientModel.getClient(context)).thenReturn(retrofit);

        IAPIClient iapiClient = PowerMockito.mock(IAPIClient.class);
        callData = mock(Call.class);
        PowerMockito.when(retrofit.create(IAPIClient.class)).thenReturn(iapiClient);
        PowerMockito.when(iapiClient.doGetRootVariant()).thenReturn(callData);

        APICallbackHandler apiCallbackHandler = mock(APICallbackHandler.class);
        PowerMockito.whenNew(APICallbackHandler.class).withAnyArguments().thenReturn(apiCallbackHandler);
        APIServiceProvider.createAPIServiceProvider(context);
    }

    @Test
    public void testResponseData(){
        Whitebox.setInternalState(APIServiceProvider.class, "s_instance", apiServiceProvider);
        Callback<Root> callback = PowerMockito.mock(Callback.class);
        Response<Root> response = PowerMockito.mock(Response.class);
        callData.enqueue(callback);
        callback.onResponse(callData,response);
        apiServiceProvider.requestData();
        verify(callback).onResponse(callData,response);

    }

    @Test
    public void testFailureData(){
        Whitebox.setInternalState(APIServiceProvider.class, "s_instance", apiServiceProvider);
        Throwable throwable = PowerMockito.mock(Throwable.class);
        Callback<Root> callback = PowerMockito.mock(Callback.class);
        callData.enqueue(callback);
        callback.onFailure(callData,throwable);
        apiServiceProvider.requestData();
        verify(callback).onFailure(callData,throwable);

    }
}
