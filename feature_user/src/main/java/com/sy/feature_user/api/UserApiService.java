package com.sy.feature_user.api;

import com.sy.feature_user.bean.ReqAddAddress;
import com.sy.feature_user.bean.ReqLogin;
import com.sy.feature_user.bean.ReqOrderPay;
import com.sy.feature_user.bean.ResOrderPay;
import com.sy.feature_user.bean.ResUserInfo;
import com.sy.libbase.bean.ResAddress;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApiService {

    /**
     * 请求登录
     *
     * @return
     */
    @POST("user.user/accountLogin")
    Call<ResBase<Object>> RequestLogin(@Body ReqLogin reqlogin);

    /**
     * 获取收货地址列表
     *
     * @return
     */
    @GET("user.address")
    Call<ResBase<List<ResAddress>>> getAddress(@Header("token") String token);

    /**
     * 添加一个收货地址
     *
     * @return
     */
    @POST("user.address/add")
    Call<ResBase<Void>> AddAddress(@Header("token") String token, @Body ReqAddAddress addAddress);

    /**
     * 获取用户信息
     * 这里主要是为了拿到余额
     *
     * @return
     */
    @GET("user.user/profile")
    Call<ResBase<ResUserInfo>> getUserInfo(@Header("token") String token);

    /**
     * 余额支付
     *
     * @return
     */
    @POST("pay/prepay")
    Call<ResBase<ResOrderPay>> requestOrderPay(@Header("token") String token, @Body ReqOrderPay orderPay);

}
