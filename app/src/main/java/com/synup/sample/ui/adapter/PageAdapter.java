package com.synup.sample.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> m_fragments;
    private List<String> m_showNameType;

    public PageAdapter(FragmentManager fm) {
        super(fm);
        m_showNameType = new ArrayList<>();
        m_fragments = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position ) {
        return m_fragments.get( position );
    }

    @Override
    public CharSequence getPageTitle( int position ) {
        return m_showNameType.size() == 0 ? "" : m_showNameType.get( position );
    }

    @Override
    public int getCount() {
        return m_showNameType.size();
    }

    public void addFrag(Fragment fragment, String title) {
        m_fragments.add(fragment);
        m_showNameType.add(title);
    }
}
