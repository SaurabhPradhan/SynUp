package com.synup.sample.ui.fragment;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.synup.sample.R;
import com.synup.sample.application.IBasePresenter;
import com.synup.sample.bean.ExcludeList;
import com.synup.sample.bean.VariantGroup;
import com.synup.sample.presenter.VariantPresenter;
import com.synup.sample.ui.adapter.VariantCombinationAdapter;
import com.synup.sample.ui.view.IDataView;
import com.synup.sample.util.Constants;

import java.util.List;
import java.util.Objects;

public class VariantCombinationFragment  extends BaseFragment implements IDataView, View.OnClickListener {

    private View variantCombinationView;
    private RecyclerView recyclerView;
    private Button bRetry;
    private VariantPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView( inflater, container, savedInstanceState );
        variantCombinationView = inflater.inflate( R.layout.variant_fragment_layout, container, false );
        initView();
        return variantCombinationView;
    }

    private void initView() {
        bRetry = Objects.requireNonNull(getActivity()).findViewById(R.id.retry);
        recyclerView = variantCombinationView.findViewById(R.id.recycler_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bRetry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(bRetry)){
            WifiManager wifi = (WifiManager) Objects.requireNonNull(getActivity()).getSystemService(Context.WIFI_SERVICE);
            if (wifi.isWifiEnabled()) {
                getActivity().findViewById(R.id.progress_circular).setVisibility(View.VISIBLE);
                presenter.onButtonClick();
            }else{
                Toast.makeText(getContext(),this.getString(R.string.turn_on_wifi),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        presenter = new VariantPresenter();
        presenter.setView( this );
        return presenter;
    }


    @Override
    public void onDataDownloadResponse(int eventId, List<VariantGroup> variantGroupData, List<List<ExcludeList>> excludeData) {
        if(eventId == Constants.RESULT_SUCCESS){
            Objects.requireNonNull(getActivity()).findViewById(R.id.retry).setVisibility(View.INVISIBLE);
            getActivity().findViewById(R.id.progress_circular).setVisibility(View.INVISIBLE);
            VariantCombinationAdapter m_variantCombinationAdapter = new VariantCombinationAdapter(excludeData,variantGroupData);
            recyclerView.setAdapter(m_variantCombinationAdapter);
        }else if(eventId == Constants.RESULT_FAILURE){
            Objects.requireNonNull(getActivity()).findViewById(R.id.progress_circular).setVisibility(View.INVISIBLE);
            Objects.requireNonNull(getActivity()).findViewById(R.id.retry).setVisibility(View.VISIBLE);
        }
    }
}
