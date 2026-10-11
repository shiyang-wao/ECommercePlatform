package com.sy.feature_categorize.ui.categorize;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.feature_categorize.bean.ResCategory;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;

import java.util.List;

public class CategoryViewModel extends BaseViewModel {
    private static final String TAG = "CategoryViewModel";

    private final CategoryModel mModel;
    private MutableLiveData<List<ResCategory.ResFirstCategory>> mDatas=new MutableLiveData<>();//整个分类的数据，一级分类至三级分类
    private MutableLiveData<String> mUrl=new MutableLiveData<>();//图片的url

    public CategoryViewModel() {
        mModel = new CategoryModel();
    }

    /**
     * 请求商品分类数据
     * @param id
     */
    public void requestCategory(int id){
        mModel.requestCategory(id, new IRequestCallback<List<ResCategory.ResFirstCategory>>() {
            @Override
            public void onLoadFinish(List<ResCategory.ResFirstCategory> datas) {
                mDatas.setValue(datas);
                Log.i(TAG, "onLoadFinish: 分类数据："+datas.size());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {

            }
        });
    }

    public MutableLiveData<List<ResCategory.ResFirstCategory>> getDatas() {
        return mDatas;
    }

    public MutableLiveData<String> getUrl() {
        return mUrl;
    }
}
