package com.sy.feature_shoppingcart.ui.pay;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.feature_shoppingcart.bean.ReqOrderCal;
import com.sy.feature_shoppingcart.bean.ResCreatOrder;
import com.sy.feature_shoppingcart.bean.ResDefaultAddress;
import com.sy.feature_shoppingcart.bean.ResOrderCal;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.bean.ResBase;

import java.util.List;

public class ConfirmOrderViewModel extends BaseViewModel {
    private static final String TAG = "ConfirmOrderViewModel";

    private final ConfirmOrderModel mModel;
    private MutableLiveData<ResDefaultAddress> mDefaultAddress=new MutableLiveData<>();
    private MutableLiveData<ResOrderCal> mCalDatas=new MutableLiveData<>();  //进行订单计算的结果
    private MutableLiveData<String> mOrderAmount=new MutableLiveData<>();  //订单金额
    private MutableLiveData<String> mDispatchAmount=new MutableLiveData<>();  //运费金额
    private MutableLiveData<String> mPromoDiscountFee=new MutableLiveData<>();  //活动优惠
    private MutableLiveData<String> mPayFee=new MutableLiveData<>();  //支付
    private MutableLiveData<String> mGoodsSum=new MutableLiveData<>();  //结算商品数量
    private MutableLiveData<Integer> mAddressId=new MutableLiveData<>();  //当前选好地址的id
    private MutableLiveData<String> mProvinceAndMunicipal=new MutableLiveData<>();  //若有地址，省市区
    private MutableLiveData<String> mDetailAddress=new MutableLiveData<>();  //若有地址，详细地址
    private MutableLiveData<String> mNameAndPhone=new MutableLiveData<>();  //若有地址，姓名和电话
    private MutableLiveData<ConfirmOrderAction> mAction = new MutableLiveData<>();
    private MutableLiveData<ResCreatOrder> mCreatOrderDatas = new MutableLiveData<>(); //创建订单结果



    public ConfirmOrderViewModel() {
        mModel = new ConfirmOrderModel();
    }

    /**
     * 获取默认收货地址
     */
    public void getDefaultAddress() {
        mModel.getDefaultAddress(new IRequestCallback<ResBase<ResDefaultAddress>>() {
            @Override
            public void onLoadFinish(ResBase<ResDefaultAddress> datas) {
                mDefaultAddress.setValue(datas.getData());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }

    /**
     * 确认订单页的详情获取
     * @param reqOrderCal
     */
    public void requestOrderCal(ReqOrderCal reqOrderCal) {
        mModel.requestOrderCal(reqOrderCal, new IRequestCallback<ResBase<ResOrderCal>>() {
            @Override
            public void onLoadFinish(ResBase<ResOrderCal> datas) {

                if (datas.getData()!=null){
                    mCalDatas.setValue(datas.getData());
                    mOrderAmount.setValue("￥"+datas.getData().getOrder_amount());
                    mDispatchAmount.setValue("+￥"+datas.getData().getDispatch_amount());
                    mPromoDiscountFee.setValue("-￥"+datas.getData().getPromo_discount_fee());
                    mPayFee.setValue("￥"+datas.getData().getPay_fee());
                    mGoodsSum.setValue("共"+datas.getData().getGoods_list().size()+"件");
                    Log.i(TAG, "onLoadFinish: 结算数据：支付："+datas.getData().getPay_fee());

                    showToast(datas.getMsg());
                }
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }

    /**
     * 跳转到收货地址页面
     */
    public void onClickGoDeliveryAddress(){
        mAction.setValue(ConfirmOrderAction.NAVIGATION_TO_DELIVERY_ADDRESS);
    }

    /**
     * 订单创建
     */
    public void creatOrder(int id, List<ReqOrderCal.GoodsListBean> goodsList){
        mModel.creatOrder(id, goodsList, new IRequestCallback<ResBase<ResCreatOrder>>() {
            @Override
            public void onLoadFinish(ResBase<ResCreatOrder> datas) {
                mCreatOrderDatas.setValue(datas.getData());
                showToast(datas.getMsg());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                showToast(message);
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }

    public MutableLiveData<ResDefaultAddress> getmDefaultAddress() {
        return mDefaultAddress;
    }
    public MutableLiveData<ResOrderCal> getCalDatas() {
        return mCalDatas;
    }

    public MutableLiveData<String> getOrderAmount() {
        return mOrderAmount;
    }

    public MutableLiveData<String> getDispatchAmount() {
        return mDispatchAmount;
    }

    public MutableLiveData<String> getPromoDiscountFee() {
        return mPromoDiscountFee;
    }

    public MutableLiveData<String> getPayFee() {
        return mPayFee;
    }

    public MutableLiveData<String> getGoodsSum() {
        return mGoodsSum;
    }

    public MutableLiveData<String> getProvinceAndMunicipal() {
        return mProvinceAndMunicipal;
    }

    public MutableLiveData<String> getDetailAddress() {
        return mDetailAddress;
    }

    public MutableLiveData<String> getNameAndPhone() {
        return mNameAndPhone;
    }

    public MutableLiveData<ConfirmOrderAction> getAction() {
        return mAction;
    }

    public MutableLiveData<Integer> getAddressId() {
        return mAddressId;
    }

    public MutableLiveData<ResCreatOrder> getCreatOrderDatas() {
        return mCreatOrderDatas;
    }

    /**
     * 枚举
     */
    public enum ConfirmOrderAction {
        NAVIGATION_TO_DELIVERY_ADDRESS,     // 跳转到收货地址页面
        NAVIGATION_TO_PAY,   // 跳转到支付页面
    }

}
