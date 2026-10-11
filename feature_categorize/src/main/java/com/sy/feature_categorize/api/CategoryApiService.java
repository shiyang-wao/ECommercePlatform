package com.sy.feature_categorize.api;
import com.sy.feature_categorize.bean.ResCategory;
import com.sy.network.bean.ResBase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryApiService {

    /**
     * 获取分类页的商品分类
     *
     * @param id 在这里固定是 21，我也不知道为什么
     * @return
     */
    @GET("category")
    Call<ResBase<ResCategory>> getCategory(@Query("id") int id);

}
