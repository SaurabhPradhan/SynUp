package com.synup.sample.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.synup.sample.R;
import com.synup.sample.ui.adapter.PageAdapter;
import com.synup.sample.ui.fragment.VariantCombinationFragment;
import com.synup.sample.ui.fragment.VariantCategoryFragment;


public class MainActivity extends AppCompatActivity{

    private int[] tabIcons = {
            R.mipmap.receipt,
            R.mipmap.pizza
    };
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewpager);
        addTabs(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

    }


    private void addTabs(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFrag(new VariantCategoryFragment(),getString(R.string.variant_category));
        adapter.addFrag(new VariantCombinationFragment(), getString(R.string.variant_combination));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        TabLayout.Tab categoryTab = tabLayout.getTabAt(0);
        if(categoryTab!=null) {
            categoryTab.setIcon(tabIcons[0]);
        }
        TabLayout.Tab combinationTab = tabLayout.getTabAt(1);
        if(combinationTab!=null){
            combinationTab.setIcon(tabIcons[1]);
        }
    }
}