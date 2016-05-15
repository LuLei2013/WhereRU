package com.whereru.greengrass.goforit.okhttp;

import okhttp3.OkHttpClient;

/**
 * Created by lulei on 16/5/12.
 * 单例化OkhttpClient,发出的所有请求都应该使用该实例
 */
public class HttpClient extends OkHttpClient {

    private HttpClient() {

    }

    private static class Innner {
        private static HttpClient mInstance = new HttpClient();
    }

    public static HttpClient getInstance() {
        return Innner.mInstance;
    }
}
