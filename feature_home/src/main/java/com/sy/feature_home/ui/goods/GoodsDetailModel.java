package com.sy.feature_home.ui.goods;

import com.sy.feature_home.api.HomeApiServiceProvider;
import com.sy.feature_home.bean.ResGoodsMoreDetail;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import retrofit2.Call;

public class GoodsDetailModel {
    /**
     * 通过商品id去请求商品详情页的数据
     * @param id
     * @param callback
     */
    public void requestGoodsDetailData(int id, IRequestCallback<ResGoodsMoreDetail> callback) {
        Call<ResBase<ResGoodsMoreDetail>> call = HomeApiServiceProvider.getApiService().getGoodsDetailById(id);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResGoodsMoreDetail>>() {
            @Override
            public void onSuccess(ResBase<ResGoodsMoreDetail> result) {
                callback.onLoadFinish(result.getData());
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
