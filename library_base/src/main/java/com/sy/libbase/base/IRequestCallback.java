package com.sy.libbase.base;

/**
 * model和ViewModel通讯的接口，model得到的结果往ViewModel传
 * @param <T>
 */
public interface IRequestCallback <T>{
    void onLoadFinish(T datas);
    void onLoadFail(int errorCode,String message);
}
