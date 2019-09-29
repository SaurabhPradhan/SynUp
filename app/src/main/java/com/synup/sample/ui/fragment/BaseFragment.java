package com.synup.sample.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.synup.sample.application.IBasePresenter;

// This abstract class is used by all the fragments through this class we create presenter
// and each view(fragments) Knows who is the presenter.

public abstract class BaseFragment extends Fragment {

    private IBasePresenter m_presenter;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        m_presenter = createPresenter();
        if ( null != m_presenter ) {
            m_presenter.onCreate( savedInstanceState );
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if ( null != m_presenter ) {
            m_presenter.onDestroy();
        }
    }

    protected abstract IBasePresenter createPresenter();
}
