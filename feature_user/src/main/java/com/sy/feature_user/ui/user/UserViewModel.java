package com.sy.feature_user.ui.user;

import android.util.Log;

import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.base.IRequestCallbackHeader;
import com.sy.network.bean.ResBase;

public class UserViewModel extends BaseViewModel {
    private static final String TAG = "UserViewModel";

    private final UserModel mModel;

    public UserViewModel() {
        mModel = new UserModel();
    }

    void requestLogin(String account, String password,IRequestCallbackHeader<ResBase<Object>> callback){
        mModel.requestLogin(account, password, new IRequestCallbackHeader<ResBase<Object>>() {
            @Override
            public void onLoadFinish(ResBase<Object> datas, String token) {
                Log.i(TAG, "onLoadFinish: token"+token);
                callback.onLoadFinish(datas,token);
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
