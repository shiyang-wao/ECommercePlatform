package com.sy.network;

import android.util.Log;

import com.sy.network.bean.ResBase;
import com.sy.network.config.ErrorStatusConfig;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 对网络请求做一个封装
 */
public class ApiCall {
    private static final String TAG = "ApiCall";

    /**
     * 普通类型数据回调
     * 在网络请求之后，往onSucess放的数据是response.body()的类型，泛型别写错
     */
    public interface ApiCallback<V> {
        void onSuccess(V result);

        void onError(int errorCode, String message);
    }

    public interface ApiCallbackHeader<V> {
        void onSuccess(V result, String token);

        void onError(int errorCode, String message);
    }

    /**
     * 发起异步请求，并且先在这里处理服务端返回的状态，
     * 如果成功，通过接口的onSucess()返回结果
     * 如果失败，则抛出错误
     * 返回数据格式基于 ResBase
     *
     * @param call
     * @param callback
     * @param <T>
     */
    public static <T> void enqueue(Call<ResBase<T>> call, ApiCallback<ResBase<T>> callback) {
        call.enqueue(new Callback<ResBase<T>>() {
            @Override
            public void onResponse(Call<ResBase<T>> call, Response<ResBase<T>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 1 && response.body() != null) {
                        Log.i(TAG, "onResponse: 数据请求成功");
                        //请求成功，并且拿到数据
                        callback.onSuccess(response.body());
                    } else {
                        Log.i(TAG, "onResponse: code不等1");
                        callback.onError(ErrorStatusConfig.ERROR_STATUS_SERVER_ERROR, response.body().getMsg());
                    }
                } else {
                    Log.i(TAG, "onResponse: nosuccessful");
                    //与服务端通讯发送http请求错误，可以通过response.code()获取错误码：404，400。。。
                    callback.onError(ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL, "网络异常，请检查网络！");
                }
            }

            @Override
            public void onFailure(Call<ResBase<T>> call, Throwable throwable) {
                Log.i(TAG, "onResponse: 网络异常");
                callback.onError(ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL, "网络异常，请检查网络！");
            }
        });
    }

    /**
     * 发起异步请求，并且先在这里处理服务端返回的状态，
     * 如果成功，通过接口的onSucess()返回结果
     * 如果失败，则抛出错误
     *
     * @param call
     * @param callback
     * @param <T>
     */
    public static <T> void enqueueCommon(Call<T> call, ApiCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    T body = response.body();
                    callback.onSuccess(body);
                } else {
                    //与服务端通讯发送http请求错误，可以通过response.code()获取错误码：404，400。。。
                    callback.onError(ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL, "网络异常，请检查网络！");
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                callback.onError(ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL, "网络异常，请检查网络！");
            }
        });
    }

    /**
     * 发起异步请求，并且先在这里处理服务端返回的状态，
     * 如果成功，通过接口的onSucess()返回结果
     * 如果失败，则抛出错误
     *
     * @param call
     * @param callback
     * @param <T>
     */
    public static <T> void enqueueBackHeader(Call<T> call, ApiCallbackHeader<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    Headers headers = response.headers();
                    // 循环打印所有响应头的键名和值
                    for (String name : headers.names()) {
                        Log.i(TAG, "响应头键名: " + name + "，值: " + headers.get(name));
                    }
                    String token = headers.get("token");
                    Log.i(TAG, "onResponse: token：" + token);
                    T body = response.body();
                    if (token != null && !token.isEmpty()) {
                        callback.onSuccess(body, token);
                    }
                } else {
                    //与服务端通讯发送http请求错误，可以通过response.code()获取错误码：404，400。。。
                    callback.onError(ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL, "网络异常，请检查网络！");
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                callback.onError(ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL, "网络异常，请检查网络！");
            }
        });
    }
}
