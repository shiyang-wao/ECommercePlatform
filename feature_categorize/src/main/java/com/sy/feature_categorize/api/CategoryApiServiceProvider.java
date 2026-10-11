package com.sy.feature_categorize.api;

import com.sy.network.RetrofitProvider;

import retrofit2.Retrofit;

public class CategoryApiServiceProvider {
    private static CategoryApiService mApiService;

    public static CategoryApiService getApiService() {
        if (mApiService == null) {
            Retrofit retrofit = RetrofitProvider.provide();
            mApiService = retrofit.create(CategoryApiService.class);
        }
        return mApiService;
    }
}
