package com.sy.feature_shoppingcart.api;

import com.sy.network.RetrofitProvider;

import retrofit2.Retrofit;

public class ShopCartApiServiceProvider {
    private static ShopCartApiService mApiService;
    public static ShopCartApiService getApiService() {
        if (mApiService == null) {
            Retrofit retrofit = RetrofitProvider.provide();
            mApiService = retrofit.create(ShopCartApiService.class);
        }
        return mApiService;
    }
}
