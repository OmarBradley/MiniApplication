package com.example.tacademy.miniapplication.tstore;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacademy.miniapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TStoreFragment extends Fragment {

    @Bind(R.id.tabhost) FragmentTabHost tabHost;

    public TStoreFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tstore, container, false);
        ButterKnife.bind(this, view);
        tabHost.setup(getContext(), getChildFragmentManager(), android.R.id.tabcontent);
        addTabHost("category", getString(R.string.tstore_tab_category_title), TStoreCategoryFragment.class);
        addTabHost("search", getString(R.string.tstore_tab_search_title), TStoreSearchFragment.class);
        /*tabHost.addTab(tabHost.newTabSpec("category").setIndicator(getString(R.string.tstore_tab_category_title)), TStoreCategoryFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("search").setIndicator(getString(R.string.tstore_tab_search_title)), TStoreSearchFragment.class, null);*/
        return view;
    }

    private void addTabHost(String tabId, @StringRes String tabName, Class fragmentClass){
        tabHost.addTab(tabHost.newTabSpec(tabId).setIndicator(tabName), fragmentClass, null);
    }


}
