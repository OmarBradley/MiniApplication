package com.example.tacademy.miniapplication.tstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.example.tacademy.miniapplication.R;
import com.example.tacademy.miniapplication.data.category.CategoryListOrder;
import com.example.tacademy.miniapplication.data.category.product.Product;
import com.example.tacademy.miniapplication.data.category.product.TStoreCategoryProductResult;
import com.example.tacademy.miniapplication.util.manager.network.NetworkManager;
import com.example.tacademy.miniapplication.util.manager.okhttpclient.TStoreOkHttpClient;
import com.example.tacademy.miniapplication.util.manager.urlparam.GetMethodParameters;
import com.example.tacademy.miniapplication.util.manager.urlparam.URLParameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TStoreAppListActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_CODE = "category_code";
    String code;
    ProductAdapter adapter;
    @Bind(R.id.rv_list)
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tstore_app_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        code = getIntent().getStringExtra(EXTRA_CATEGORY_CODE);

        adapter = new ProductAdapter();
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
        setData();
        adapter.setOnItemClickListener((view, product) -> {
            Intent intent = new Intent(TStoreAppListActivity.this, TStoreDetailActivity.class);
            intent.setData(Uri.parse(product.getWebUrl()));
            startActivity(intent);
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData() {
        NetworkManager.getInstance().setOKhttpClient(new TStoreOkHttpClient());
        try {
            NetworkManager.getInstance().getResult(makeGetParameters.apply("DP"), TStoreCategoryProductResult.class, (request, result) -> {
                adapter.clear();
                TStoreCategoryProductResult resultData = (TStoreCategoryProductResult) result;
                Stream.of(resultData.getProducts().getProducts()).forEach(adapter::add);
            }, (request, code, cause) -> {
                Toast.makeText(TStoreAppListActivity.this, "fail...", Toast.LENGTH_SHORT).show();
            });
        } catch (UnsupportedEncodingException e) {
            Log.e("support", e.toString());
        }
    }

    Function<String, URLParameters> makeGetParameters = (code) -> {
        Map<String, String> headerParameters = new HashMap<>();
        headerParameters.put("Accept", "application/json");
        headerParameters.put("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a");

        String URL_FORMAT = "http://apis.skplanetx.com/tstore/categories/%s";
        String url = null;
        try{
            url = String.format(URL_FORMAT, URLEncoder.encode(code,"utf-8"));
        } catch (UnsupportedEncodingException e){
            Log.e("sss", e.toString());
        }
        Log.e("url", url);

        Map<String, String> queryParameter = new HashMap<>();
        queryParameter.put("version", "1");
        queryParameter.put("page", "1");
        queryParameter.put("count", "10");
        queryParameter.put("order", CategoryListOrder.BEST_C.getOrderChar());
        return new GetMethodParameters(this, url, queryParameter, headerParameters);
    };


}
