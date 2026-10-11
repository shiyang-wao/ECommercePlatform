package com.sy.feature_categorize.ui.categorize;

import com.sy.feature_categorize.api.CategoryApiServiceProvider;
import com.sy.feature_categorize.bean.ResCategory;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;

public class CategoryModel {
    /**
     * 获取分类页的商品分类数据
     * @param id
     * @param callback
     */
    public void requestCategory(int id, IRequestCallback<List<ResCategory.ResFirstCategory>> callback){
        Call<ResBase<ResCategory>> call = CategoryApiServiceProvider.getApiService().getCategory(id);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResCategory>>() {
            @Override
            public void onSuccess(ResBase<ResCategory> result) {
                callback.onLoadFinish(result.getData().getChildren());
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
