package com.sy.feature_home.ui.goods;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.feature_home.bean.ResGoodsMoreDetail;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;

import java.util.List;

public class GoodsDetailViewModel extends BaseViewModel {
    private static final String TAG = "GoodsDetailViewModel";

    private final GoodsDetailModel mModel;
    private MutableLiveData<ResGoodsMoreDetail> mDatas = new MutableLiveData<>();
    private MutableLiveData<String> mPrice = new MutableLiveData<>(); //售价
    private MutableLiveData<String> mSale = new MutableLiveData<>(); //已售数量
    private MutableLiveData<String> mGuarantee = new MutableLiveData<>(); //有哪些保障
    private MutableLiveData<String> mParams = new MutableLiveData<>(); //有哪些参数

    public GoodsDetailViewModel() {
        mModel = new GoodsDetailModel();
    }

    /**
     * 请求商品详情页的数据
     *
     * @param id
     */
    public void requestGoodsDetailData(int id) {
        mModel.requestGoodsDetailData(id, new IRequestCallback<ResGoodsMoreDetail>() {
            @Override
            public void onLoadFinish(ResGoodsMoreDetail datas) {
                mDatas.setValue(datas);
                mPrice.setValue("￥" + datas.getPrice().get(0));
                mSale.setValue("已售" + datas.getSales());
                mGuarantee.setValue(getGuaranteeString(datas));
                mParams.setValue(getParamsString(datas));
                Log.i(TAG, "onLoadFinish: " + datas.getTitle());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: message:" + message);
            }
        });
    }

    /**
     * 拼接商品保障的字符串
     * @param datas
     */
    private String getGuaranteeString(ResGoodsMoreDetail datas) {
        List<ResGoodsMoreDetail.ServiceBean> service = datas.getService();
        StringBuilder guarantee=new StringBuilder();
        if (!service.isEmpty()) {
            for (ResGoodsMoreDetail.ServiceBean gte : service) {
                guarantee.append(gte.getName()).append(" · ");
            }
            // 移除最后一个多余的 ·
            if (guarantee.length() > 0) {
                guarantee.setLength(guarantee.length() - 3);
            }
        }
        return guarantee.toString();
    }

    /**
     * 拼接商品参数的字符串
     * @param datas
     */
    private String getParamsString(ResGoodsMoreDetail datas) {
        List<ResGoodsMoreDetail.ParamsBean> params = datas.getParams();
        StringBuilder paramStr=new StringBuilder();
        if (!params.isEmpty()) {
            for (ResGoodsMoreDetail.ParamsBean param : params) {
                paramStr.append(param.getTitle()).append(" · ");
            }
            // 移除最后一个多余的 ·
            if (paramStr.length() > 0) {
                paramStr.setLength(paramStr.length() - 3);
            }
        }
        return paramStr.toString();
    }

    public MutableLiveData<ResGoodsMoreDetail> getDatas() {
        return mDatas;
    }

    public MutableLiveData<String> getPrice() {
        return mPrice;
    }

    public MutableLiveData<String> getSale() {
        return mSale;
    }

    public MutableLiveData<String> getGuarantee() {
        return mGuarantee;
    }

    public MutableLiveData<String> getParams() {
        return mParams;
    }
}
