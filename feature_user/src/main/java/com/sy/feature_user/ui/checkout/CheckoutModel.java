package com.sy.feature_user.ui.checkout;

import android.util.Log;

import com.sy.feature_user.api.UserApiServiceProvider;
import com.sy.feature_user.bean.ReqOrderPay;
import com.sy.feature_user.bean.ResOrderPay;
import com.sy.feature_user.bean.ResUserInfo;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.manager.UserManager;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import retrofit2.Call;

public class CheckoutModel {
    private static final String TAG = "CheckoutModel";

    /**
     * 拿到用户信息
     */
    public void getUserInfo(IRequestCallback<ResUserInfo> callback) {
        String token = UserManager.getInstance().getToken();
        Call<ResBase<ResUserInfo>> call = UserApiServiceProvider.getApiService().getUserInfo(token);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResUserInfo>>() {
            @Override
            public void onSuccess(ResBase<ResUserInfo> result) {
                callback.onLoadFinish(result.getData());
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    /**
     * 订单支付
     */
    public void requestOrderPay(ReqOrderPay orderPay, IRequestCallback<ResBase<ResOrderPay>> callback) {
        String token = UserManager.getInstance().getToken();
        Call<ResBase<ResOrderPay>> call = UserApiServiceProvider.getApiService().requestOrderPay(token, orderPay);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResOrderPay>>() {
            @Override
            public void onSuccess(ResBase<ResOrderPay> result) {
                Log.i(TAG, "onSuccess: "+result.getMsg());
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
