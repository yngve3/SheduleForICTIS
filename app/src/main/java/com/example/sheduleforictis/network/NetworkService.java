package com.example.sheduleforictis.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService instance;
    private final String BASE_URL = "https://webictis.sfedu.ru/";
    private final Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }

        return instance;
    }

    public ApiHelper getApi() {
        return retrofit.create(ApiHelper.class);
    }
}
