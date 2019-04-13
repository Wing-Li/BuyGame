package com.lyl.buygame.network;

import com.lyl.buygame.BuildConfig;
import com.lyl.buygame.network.api.GameApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
public class Network {

    private static final int DEFAULT_TIMEOUT = 15;

    private final static String URL_MY_API = BuildConfig.PARSE_SERVER_URL;

    private static OkHttpClient mHttpClient;

    private static GameApi mGameApi;

    static {
        // 初始化 okhttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        // debug 模式加上 log
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging);
        }
        mHttpClient = httpClientBuilder.build();
    }

    /**
     * GameApi
     */
    public static GameApi getGameApi() {
        if (mGameApi == null) {
            Retrofit retrofit = new Retrofit.Builder()//
                    .client(mHttpClient)//
                    .baseUrl(URL_MY_API)//
                    .addConverterFactory(GsonConverterFactory.create())//
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mGameApi = retrofit.create(GameApi.class);
        }
        return mGameApi;
    }

}
