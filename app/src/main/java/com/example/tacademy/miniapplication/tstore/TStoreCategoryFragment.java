package com.example.tacademy.miniapplication.tstore;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;
import com.example.tacademy.miniapplication.R;
import com.example.tacademy.miniapplication.data.category.TStoreCategoryResult;
import com.example.tacademy.miniapplication.util.manager.network.NetworkManager;
import com.example.tacademy.miniapplication.util.manager.okhttpclient.TStoreOkHttpClient;
import com.example.tacademy.miniapplication.util.manager.urlparam.GetMethodParameters;
import com.example.tacademy.miniapplication.util.manager.urlparam.URLParameters;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TStoreCategoryFragment extends Fragment {

    @Bind(R.id.rv_list)
    RecyclerView listView;
    CategoryAdapter adapter;

    public TStoreCategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CategoryAdapter();
        adapter.setOnItemClickListener((view, category) -> {
            Toast.makeText(getContext(), "Code : " + category.getCategoryCode(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), TStoreAppListActivity.class);
            intent.putExtra(TStoreAppListActivity.EXTRA_CATEGORY_CODE, category.getCategoryCode());
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tstore_category, container, false);
        ButterKnife.bind(this, view);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {
        NetworkManager.getInstance().setOKhttpClient(new TStoreOkHttpClient());
        try {
            NetworkManager.getInstance().getResult(makeGetParameters.get(), TStoreCategoryResult.class, (request, result) -> {
                adapter.clear();
                TStoreCategoryResult categoryResult = (TStoreCategoryResult) result;
                Stream.of(categoryResult.getTstore().getCategories().getCategory()).forEach(adapter::add);
            }, (request, code, cause) -> {
                Toast.makeText(getActivity(), "fail...", Toast.LENGTH_SHORT).show();
            });
        } catch (UnsupportedEncodingException e) {
            Log.e("support", e.toString());
        }
    }

    Supplier<URLParameters> makeGetParameters = () -> {
        Map<String, String> headerParameters = new HashMap<>();
        headerParameters.put("Accept", "application/json");
        headerParameters.put("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a");

        String URL_FORMAT = "http://apis.skplanetx.com/tstore/categories";

        Map<String, String> queryParameter = new HashMap<>();
        queryParameter.put("version", "1");
        return new GetMethodParameters(getActivity(), URL_FORMAT, queryParameter, headerParameters);
    };

}
