package com.sy.libbase.base;

/**
 * 返回header里面的数据时用这个
 *
 * @param <T>
 */
public interface IRequestCallbackHeader<T> {


    void onLoadFinish(T datas,String token);

    void onLoadFail(int errorCode, String message);
}
