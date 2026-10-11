package com.sy.feature_shoppingcart.ui.shopcart;

import com.sy.feature_shoppingcart.api.ShopCartApiServiceProvider;
import com.sy.feature_shoppingcart.bean.ReqUpdateCart;
import com.sy.feature_shoppingcart.bean.ResCartList;
import com.sy.feature_shoppingcart.bean.ResUpdateCart;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.manager.UserManager;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;

public class ShopcartModel {
    /**
     *更新购物车 就是添加一个商品
     * @param goods_id 商品id
     * @param goods_num 商品数量
     * @param goods_sku_price_id 商品规格id 如 "粉色/M" 是sku_price中的一组规格，其id为89，售价为XXX,这里就是id
     * @param type 若是商品详情页，是inc,若在购物车里增加数量，是cover
     * @param callback
     */
    public void updateCart(int goods_id, int goods_num, int goods_sku_price_id, String type, IRequestCallback<ResBase<ResUpdateCart>> callback){
        String token = UserManager.getInstance().getToken();
        ReqUpdateCart reqUpdateCart = new ReqUpdateCart(goods_id, goods_num, goods_sku_price_id, type);
        Call<ResBase<ResUpdateCart>> call = ShopCartApiServiceProvider.getApiService().updateCart(token, reqUpdateCart);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<ResUpdateCart>>() {
            @Override
            public void onSuccess(ResBase<ResUpdateCart> result) {
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    /**
     *获取购物车列表
     *
     * @param callback
     */
    public void getCartList(IRequestCallback<ResBase<List<ResCartList>>> callback){
        String token = UserManager.getInstance().getToken();
        Call<ResBase<List<ResCartList>>> call = ShopCartApiServiceProvider.getApiService().getCart(token);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<List<ResCartList>>>() {
            @Override
            public void onSuccess(ResBase<List<ResCartList>> result) {
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    /**
     * 单个删除购物车商品
     * @param cartId 购物车ID
     * @param callback 回调
     */
    public void deleteCart(String cartId, IRequestCallback<ResBase<Void>> callback) {
        String token = UserManager.getInstance().getToken();
        Call<ResBase<Void>> call = ShopCartApiServiceProvider.getApiService().deleteCart(token, cartId);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<Void>>() {
            @Override
            public void onSuccess(ResBase<Void> result) {
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
