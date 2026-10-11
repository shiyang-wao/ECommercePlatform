package com.sy.libbase.api;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.sy.network.bean.ResBase;

public interface CartService extends IProvider {
    void update(int goods_id, int goods_num, int goods_sku_price_id, String type, CartCallback callback);


    // 登录结果回调（base库中定义，用于传递成功/失败信息）
    public interface CartCallback {
        void onSuccess(ResBase data); // 更新成功，返回用户信息
        void onFailure(String errorMsg);   // 更新失败，返回错误信息
    }
}
