package com.example.tacademy.miniapplication.util.manager.okhttpclient;

import okhttp3.OkHttpClient;

/**
 * Created by Tacademy on 2016-05-09.
 */
public abstract class OKHttpClientFactory {
    OkHttpClient.Builder builder;

    public final OkHttpClient buildOkHttpClient() {
        builder = new OkHttpClient().newBuilder();
        setCookieOfOKHttpClientBuilder();
        setClientCacheOfOKHttpClientBuilder();
        setTimeOutOfOKHttpClientBuilder();
        return builder.build();
    }

    abstract protected void setCookieOfOKHttpClientBuilder();

    abstract protected void setClientCacheOfOKHttpClientBuilder();

    abstract protected void setTimeOutOfOKHttpClientBuilder();
}
