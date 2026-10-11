package com.sy.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sy.network.adapter.HomeItemTypeAdapter;
import com.sy.network.bean.ResHomeItem;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static final String BASE_URL = "http://shop.fzqq.fun/addons/shopro/";
    //单例模式这个静态成员变量得是私有的，只能通过provide()这个public方法去访问
    private static Retrofit mRetrofit;
    private static Retrofit mRetrofitDifAda;

    /**
     * 单例设计模式
     * <p>
     * 这样不论哪里需要一个Retrofit对象，都可以复用同一个
     *
     * @return
     */
    public static Retrofit provide() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkhttpClientProvider.provide())
                    .addConverterFactory(GsonConverterFactory.create())// 配置 Gson 转换器
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 首页初始化返回的json数据复杂，HomeItem 字段里面的 data 因 type 不同而不同，
     * 解析时需要使用自己重写的TypeAdapter，在这里使用自己提供的 HomeItemTypeAdapter 来解析
     *
     * @return
     */
    public static Retrofit provideDifAda() {
        if (mRetrofitDifAda == null) {
            // 创建 Gson 实例并注册 TypeAdapter
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ResHomeItem.class, new HomeItemTypeAdapter())
                    .create();

            // 配置 Retrofit
            mRetrofitDifAda = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkhttpClientProvider.provide())
                    .addConverterFactory(GsonConverterFactory.create(gson))// 配置 Gson 转换器
                    .build();
        }
        return mRetrofitDifAda;
    }
}
