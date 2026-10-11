package com.sy.feature_home.api;

import com.sy.feature_home.bean.ResGoodsDetail;
import com.sy.feature_home.bean.ResGoodsMoreDetail;
import com.sy.feature_home.bean.ResHomeInit;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface HomeApiService {

    /**
     * 首页初始化数据
     * 首页接口访问的时候需要在header中传一个“platform=h5”的参数
     *
     * @return
     */
    @GET("index/init")
    Call<ResBase<ResHomeInit>> getHomeInit(@Header("platform") String platform);

    /**
     * 通过商品id去获取多个商品的详情
     *
     * @param ids 多个id拼成 如：17,18,19
     * @return
     */
    @GET("goods.goods/ids")
    Call<ResBase<List<ResGoodsDetail>>> getGoodsByIds(@Query(value = "ids", encoded = true) String ids);

    /**
     * 通过商品id去获取商品的详情
     *
     * @param id
     * @return
     */
    @GET("goods.goods/detail")
    Call<ResBase<ResGoodsMoreDetail>> getGoodsDetailById(@Query("id") int id);

}
