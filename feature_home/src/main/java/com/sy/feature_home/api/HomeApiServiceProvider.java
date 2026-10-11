package com.sy.feature_home.api;

import com.sy.network.RetrofitProvider;

import retrofit2.Retrofit;

public class HomeApiServiceProvider {
    private static HomeApiService mApiService;
    private static HomeApiService mApiServiceDifAda;

    public static HomeApiService getApiServiceDifAda() {
        if (mApiServiceDifAda == null) {
            Retrofit retrofit = RetrofitProvider.provideDifAda();
            mApiServiceDifAda = retrofit.create(HomeApiService.class);
        }
        return mApiServiceDifAda;
    }
    public static HomeApiService getApiService() {
        if (mApiService == null) {
            Retrofit retrofit = RetrofitProvider.provide();
            mApiService = retrofit.create(HomeApiService.class);
        }
        return mApiService;
    }
}
