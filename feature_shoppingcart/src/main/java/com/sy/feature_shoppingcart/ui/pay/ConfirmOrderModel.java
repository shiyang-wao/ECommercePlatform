package com.sy.feature_shoppingcart.ui.pay;

import android.util.Log;

import com.sy.feature_shoppingcart.api.ShopCartApiServiceProvider;
import com.sy.feature_shoppingcart.bean.ReqOrderCal;
import com.sy.feature_shoppingcart.bean.ResCreatOrder;
import com.sy.feature_shoppingcart.bean.ResDefaultAddress;
import com.sy.feature_shoppingcart.bean.ResOrderCal;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.manager.UserManager;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;

public class ConfirmOrderModel {
    private static final String TAG = "ConfirmOrderModel";
    /**
     * 获取默认收货地址
     */
    public void getDefaultAddress(IRequestCallback<ResBase<ResDefaultAddress>> callback) {
        String token = UserManager.getInstance().getToken();
        Call<ResBase<ResDefaultAddress>> call = ShopCartApiServiceProvider.getApiService().getDefaultAddress(token);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResDefaultAddress>>() {
            @Override
            public void onSuccess(ResBase<ResDefaultAddress> result) {
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    /**
     * 订单计算
     * @param reqOrderCal
     * @param callback
     */
    public void requestOrderCal(ReqOrderCal reqOrderCal, IRequestCallback<ResBase<ResOrderCal>> callback) {
        String token = UserManager.getInstance().getToken();
        Call<ResBase<ResOrderCal>> call = ShopCartApiServiceProvider.getApiService().requestOrderCal(token, reqOrderCal);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResOrderCal>>() {
            @Override
            public void onSuccess(ResBase<ResOrderCal> result) {
                Log.i(TAG, "onSuccess: 结算数据："+result.getData().getPay_fee());
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    /**
     * 订单创建
     */
    public void creatOrder(int id, List<ReqOrderCal.GoodsListBean> goodsList,IRequestCallback<ResBase<ResCreatOrder>> callback){
        String token = UserManager.getInstance().getToken();
        ReqOrderCal reqOrderCal = new ReqOrderCal("goods", "cart", id, goodsList);
        Call<ResBase<ResCreatOrder>> call = ShopCartApiServiceProvider.getApiService().creatOrder(token, reqOrderCal);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResCreatOrder>>() {
            @Override
            public void onSuccess(ResBase<ResCreatOrder> result) {
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
