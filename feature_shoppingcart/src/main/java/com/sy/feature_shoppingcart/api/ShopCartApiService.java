package com.sy.feature_shoppingcart.api;
import com.sy.feature_shoppingcart.bean.ReqOrderCal;
import com.sy.feature_shoppingcart.bean.ReqUpdateCart;
import com.sy.feature_shoppingcart.bean.ResCartList;
import com.sy.feature_shoppingcart.bean.ResCreatOrder;
import com.sy.feature_shoppingcart.bean.ResDefaultAddress;
import com.sy.feature_shoppingcart.bean.ResOrderCal;
import com.sy.feature_shoppingcart.bean.ResUpdateCart;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShopCartApiService {

    /**
     * 更新购物车，也就是创建一条购物车数据到购物车列表
     *
     * @return
     */
    @POST("cart/update")
    Call<ResBase<ResUpdateCart>> updateCart(@Header("token") String token, @Body ReqUpdateCart updateCart);

    /**
     * 获取购物车列表
     *
     * @return
     */
    @GET("cart")
    Call<ResBase<List<ResCartList>>> getCart(@Header("token") String token);

    /**
     * 删除购物车数据  多个id用,号隔开
     * @param token
     * @param cartId
     * @return
     */
    @POST("cart/delete")
    Call<ResBase<Void>> deleteCart(@Header("token") String token, @Query("id") String cartId);

    /**
     * 获取默认收货地址
     *
     * @return
     */
    @GET("user.address/default")
    Call<ResBase<ResDefaultAddress>> getDefaultAddress(@Header("token") String token);

    /**
     * 确认订单页面获取订单信息
     * @param token
     * @param orderCal
     * @return
     */
    @POST("order.order/calc")
    Call<ResBase<ResOrderCal>> requestOrderCal(@Header("token") String token, @Body ReqOrderCal orderCal);

    /**
     * 确认订单页面创建一个订单
     * @param token
     * @param orderCal
     * @return
     */
    @POST("order.order/create")
    Call<ResBase<ResCreatOrder>> creatOrder(@Header("token") String token, @Body ReqOrderCal orderCal);

}
