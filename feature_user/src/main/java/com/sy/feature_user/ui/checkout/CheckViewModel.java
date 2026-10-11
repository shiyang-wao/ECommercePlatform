package com.sy.feature_user.ui.checkout;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.feature_user.bean.ReqOrderPay;
import com.sy.feature_user.bean.ResOrderPay;
import com.sy.feature_user.bean.ResUserInfo;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.bean.ResBase;

public class CheckViewModel extends BaseViewModel {
    private static final String TAG = "CheckViewModel";

    private final CheckoutModel mModel;
    private MutableLiveData<String> mPayFee =new MutableLiveData<>();
    MutableLiveData<String> mCountDownText=new MutableLiveData<>("剩余支付时间 00:14:49");//倒计时控件显示的文本
    MutableLiveData<ResUserInfo> mUserInfo=new MutableLiveData<>();//用户信息数据
    MutableLiveData<String> mMoney=new MutableLiveData<>();//用户余额
    MutableLiveData<Boolean> mIsBalancePay=new MutableLiveData<>(false);//是否余额支付
    MutableLiveData<ResOrderPay> mOrderPayDatas=new MutableLiveData<>();//余额支付完成的数据

    public CheckViewModel() {
        mModel = new CheckoutModel();
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo(){
        mModel.getUserInfo(new IRequestCallback<ResUserInfo>() {
            @Override
            public void onLoadFinish(ResUserInfo datas) {
                mUserInfo.setValue(datas);
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }

    /**
     * 订单支付
     */
    public void requestOrderPay(ReqOrderPay orderPay){
        mModel.requestOrderPay(orderPay, new IRequestCallback<ResBase<ResOrderPay>>() {
            @Override
            public void onLoadFinish(ResBase<ResOrderPay> datas) {
                mOrderPayDatas.setValue(datas.getData());
                showToast(datas.getMsg());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }
    public MutableLiveData<String> getPayFee() {
        return mPayFee;
    }

    public MutableLiveData<String> getCountDownText() {
        return mCountDownText;
    }

    public MutableLiveData<ResUserInfo> getmUserInfo() {
        return mUserInfo;
    }

    public MutableLiveData<String> getMoney() {
        return mMoney;
    }

    public MutableLiveData<Boolean> getIsBalancePay() {
        return mIsBalancePay;
    }

    public MutableLiveData<ResOrderPay> getOrderPayDatas() {
        return mOrderPayDatas;
    }
}
