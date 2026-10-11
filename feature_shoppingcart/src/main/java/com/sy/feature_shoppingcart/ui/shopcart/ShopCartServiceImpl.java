package com.sy.feature_shoppingcart.ui.shopcart;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sy.feature_shoppingcart.bean.ResUpdateCart;
import com.sy.libbase.api.CartService;
import com.sy.libbase.api.LoginService;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.base.IRequestCallbackHeader;
import com.sy.libbase.config.ARouterPath;
import com.sy.network.bean.ResBase;

/**
 * 用@Route注解标记服务实现类
 *
 */
@Route(path = ARouterPath.Shopcart.SERVICE_SHOP_CART)
public class ShopCartServiceImpl implements CartService {
    private static final String TAG = "UserLoginServiceImpl";
    private Context mContext;
    private ShopcartViewModel cartViewModel; // 管理登录状态的ViewModel

    @Override
    public void init(Context context) {
        this.mContext = context.getApplicationContext(); // 确保是 Application 上下文
        // 用 ViewModelProvider.AndroidViewModelFactory 创建 ViewModel（无需依赖 Application 作为 ViewModelStoreOwner）
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(
                (Application) context.getApplicationContext()
        );
        // 通过 Factory 获取 ViewModel，无类型匹配问题
        cartViewModel = new ViewModelProvider(
                new ViewModelStore(), // 手动创建 ViewModelStore（存储 ViewModel）
                factory
        ).get(ShopcartViewModel.class);
    }

    @Override
    public void update(int goods_id, int goods_num, int goods_sku_price_id, String type, CartCallback callback) {
        cartViewModel.updateCart(goods_id, goods_num, goods_sku_price_id, type, new IRequestCallback<ResBase<ResUpdateCart>>() {
            @Override
            public void onLoadFinish(ResBase<ResUpdateCart> datas) {
                callback.onSuccess(datas);
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                callback.onFailure(message);
            }
        });
    }
}
