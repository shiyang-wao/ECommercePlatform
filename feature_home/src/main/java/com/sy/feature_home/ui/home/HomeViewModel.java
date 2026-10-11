package com.sy.feature_home.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.feature_home.bean.ResGoodsDetail;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.bean.ResHomeItem;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel {
    private static final String TAG = "HomeViewModel";
    private final HomeModel mModel;
    private MutableLiveData<List<ResHomeItem>> mDatas=new MutableLiveData<>();
    private MutableLiveData<List<ResGoodsDetail>> mGoodsDetailDatas=new MutableLiveData<>();
    private MutableLiveData<String> mIds=new MutableLiveData<>();  //多个商品id，进行商品详情的网络请求
    public HomeViewModel() {
        mModel = new HomeModel();
    }

    /**
     * 请求首页初始化的数据，将数据依 type 不同而解析成不同的类
     */
    public void requestDatas() {
        mModel.requestDatas(new IRequestCallback<List<ResHomeItem>>() {
            @Override
            public void onLoadFinish(List<ResHomeItem> homeItems) {
                mDatas.setValue(homeItems);
                for (ResHomeItem item : homeItems) {
                    switch (item.getType()) {
                        case "goodsCard":
                            ResHomeItem.GoodsCardData goodsData = (ResHomeItem.GoodsCardData) item.getData();
                            List<Integer> goodsIds = goodsData.getGoodsIds();
                            StringBuilder idsBuilder = new StringBuilder();
                            if (!goodsIds.isEmpty()) {
                                for (int id : goodsIds) {
                                    idsBuilder.append(id).append(",");
                                }
                                // 移除最后一个多余的逗号
                                if (idsBuilder.length() > 0) {
                                    idsBuilder.setLength(idsBuilder.length() - 1);
                                }
                            }
                            String ids = idsBuilder.toString();
                            mIds.setValue(ids);
                            Log.i(TAG, "onSuccess: 商品id数："+goodsIds.size());
                            Log.i(TAG, "onSuccess: 拼接的ids："+ids);
                            break;
                        default:
                            // 处理未知类型或记录日志
                            Log.w("HomeData", "未知的组件类型: " + item.getType());
                            break;
                    }
                }
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: message"+message);
            }
        });
    }



    /**
     * 通过id去获取商品详情，一次可传入多个id
     * @param ids
     */
    public void requestGoodsDetailByIds(String ids) {
        mModel.requestGoodsDetailByIds(ids, new IRequestCallback<List<ResGoodsDetail>>() {
            @Override
            public void onLoadFinish(List<ResGoodsDetail> datas) {
                mGoodsDetailDatas.setValue(datas);
            }

            @Override
            public void onLoadFail(int errorCode, String message) {

            }
        });
    }
    public MutableLiveData<List<ResHomeItem>> getDatas() {
        return mDatas;
    }

    public MutableLiveData<String> getIds() {
        return mIds;
    }

    public MutableLiveData<List<ResGoodsDetail>> getGoodsDetailDatas() {
        return mGoodsDetailDatas;
    }
}
