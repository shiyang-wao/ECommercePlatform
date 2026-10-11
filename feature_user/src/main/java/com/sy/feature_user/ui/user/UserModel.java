package com.sy.feature_user.ui.user;

import android.util.Log;

import com.sy.feature_user.api.UserApiServiceProvider;
import com.sy.feature_user.bean.ReqLogin;
import com.sy.libbase.base.IRequestCallbackHeader;
import com.sy.libbase.manager.UserManager;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import retrofit2.Call;

public class UserModel {
    private static final String TAG = "UserModel";


    /**
     * 通过账号密码登录
     * @param account
     * @param password
     * @param callback
     */
    public void requestLogin(String account, String password, IRequestCallbackHeader<ResBase<Object>> callback){
        ReqLogin reqLogin = new ReqLogin(account, password);
        Call<ResBase<Object>> call = UserApiServiceProvider.getApiService().RequestLogin(reqLogin);
        ApiCall.enqueueBackHeader(call, new ApiCall.ApiCallbackHeader<ResBase<Object>>() {
            @Override
            public void onSuccess(ResBase<Object> result, String token) {
                //将请求登录获取到的token值存入sp数据库
                UserManager instance = UserManager.getInstance();
                instance.saveToken(token);
                callback.onLoadFinish(result,token);
                Log.i(TAG, "onSuccess: token:"+token);
            }

            @Override
            public void onError(int errorCode, String message) {
                Log.i(TAG, "onError: mes"+message);
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
