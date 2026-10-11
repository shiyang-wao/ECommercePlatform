package com.sy.feature_user.api;

import com.sy.network.RetrofitProvider;

import retrofit2.Retrofit;

public class UserApiServiceProvider {
    private static UserApiService mApiService;

    public static UserApiService getApiService() {
        if (mApiService == null) {
            Retrofit retrofit = RetrofitProvider.provide();
            mApiService = retrofit.create(UserApiService.class);
        }
        return mApiService;
    }
}
