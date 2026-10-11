package com.sy.feature_user.ui.user;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sy.libbase.api.LoginService;
import com.sy.libbase.base.IRequestCallbackHeader;
import com.sy.libbase.config.ARouterPath;
import com.sy.network.bean.ResBase;

/**
 * 用@Route注解标记服务实现类
 *
 * 登录服务每个模块都能触发，所以单独封装，不依赖 UserFragment的生命周期
 */
@Route(path = ARouterPath.User.SERVICE_USER_LOGIN)
public class UserLoginServiceImpl implements LoginService {
    private static final String TAG = "UserLoginServiceImpl";
    private Context mContext;
    private UserModel userModel; // 登录网络请求的Model（MVVM数据层）
    private UserViewModel userViewModel; // 管理登录状态的ViewModel

    @Override
    public void login(String account, String password, LoginCallback callback) {
        userViewModel.requestLogin(account, password, new IRequestCallbackHeader<ResBase<Object>>() {
            @Override
            public void onLoadFinish(ResBase<Object> datas, String token) {
                Log.i(TAG, "onLoadFinish: token"+token);
                callback.onSuccess(datas,token);
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                callback.onFailure(message);
            }
        });
    }

    @Override
    public void init(Context context) {
        this.mContext = context.getApplicationContext(); // 确保是 Application 上下文
        // 用 ViewModelProvider.AndroidViewModelFactory 创建 ViewModel（无需依赖 Application 作为 ViewModelStoreOwner）
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(
                (Application) context.getApplicationContext()
        );
        // 通过 Factory 获取 ViewModel，无类型匹配问题
        userViewModel = new ViewModelProvider(
                new ViewModelStore(), // 手动创建 ViewModelStore（存储 ViewModel）
                factory
        ).get(UserViewModel.class);

//        // 初始化Model和ViewModel（根据项目实际情况获取，比如通过ViewModelProvider）
//        userModel = new UserModel();
//        userViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(UserViewModel.class);
    }
}
