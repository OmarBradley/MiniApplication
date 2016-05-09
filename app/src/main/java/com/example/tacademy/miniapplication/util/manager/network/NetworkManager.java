package com.example.tacademy.miniapplication.util.manager.network;

import android.os.Handler;
import android.os.Looper;

import com.annimon.stream.function.BiConsumer;
import com.example.tacademy.miniapplication.util.functionalinterface.TriConsumer;
import com.example.tacademy.miniapplication.util.manager.cancel.RequestCancel;
import com.example.tacademy.miniapplication.util.manager.okhttpclient.OKHttpClientFactory;
import com.example.tacademy.miniapplication.util.manager.urlparam.URLParameters;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Tacademy on 2016-05-09.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NetworkManager {

    private static final class SingletonHolder {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }

    public static NetworkManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Getter
    private OkHttpClient client;
    @Getter
    private RequestCancel requestCancel;

    public void setOKhttpClient(OKHttpClientFactory OKHttpClient){
        client = OKHttpClient.buildOkHttpClient();
        requestCancel = new RequestCancel(client);
    }

    public <T> Request getResult(URLParameters urlParameters, Class<T> resultDataClass, BiConsumer<Request, T> onSuccess, TriConsumer<Request, Integer, Throwable> onFailure) throws UnsupportedEncodingException {
        Request request = urlParameters.makeRequest();
        Handler handler = new Handler(Looper.getMainLooper());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(() -> {
                    onFailure.accept(request, -1, e);
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    T searchResult = gson.fromJson(response.body().charStream(), resultDataClass);
                    handler.post(() -> {
                        onSuccess.accept(request, searchResult);
                    });
                } else {
                    throw new IOException(response.message()); // onFailure 함수로 간다. --> detail 하게 하려면 handler를 이용해서 이 부분도 직접 처리해줘야 한다.
                }

            }
        });
        return request;
    }










}
