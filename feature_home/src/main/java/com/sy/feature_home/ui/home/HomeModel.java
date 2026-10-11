package com.sy.feature_home.ui.home;

import android.util.Log;

import com.sy.feature_home.api.HomeApiServiceProvider;
import com.sy.feature_home.bean.ResGoodsDetail;
import com.sy.feature_home.bean.ResHomeInit;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;
import com.sy.network.bean.ResHomeItem;

import java.util.List;

import retrofit2.Call;

public class HomeModel {

    private static final String TAG = "HomeModel";

    /**
     * 请求首页初始化的数据
     * @param callback
     */
    public void requestDatas(IRequestCallback<List<ResHomeItem>> callback) {
        Call<ResBase<ResHomeInit>> Call = HomeApiServiceProvider.getApiServiceDifAda().getHomeInit("h5");
        ApiCall.enqueue(Call, new ApiCall.ApiCallback<ResBase<ResHomeInit>>() {
            @Override
            public void onSuccess(ResBase<ResHomeInit> result) {
                List<ResHomeItem> homeItems = result.getData().getTemplate().getHome().getData();
                callback.onLoadFinish(homeItems);
            }

            @Override
            public void onError(int errorCode, String message) {
                Log.i(TAG, "onError: mes"+message);
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    public void requestGoodsDetailByIds(String ids,IRequestCallback<List<ResGoodsDetail>> callback){
        Log.i(TAG, "requestGoodsDetailByIds: "+ids);
        Call<ResBase<List<ResGoodsDetail>>> call = HomeApiServiceProvider.getApiService().getGoodsByIds(ids);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<List<ResGoodsDetail>>>() {
            @Override
            public void onSuccess(ResBase<List<ResGoodsDetail>> result) {
                callback.onLoadFinish(result.getData());
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
