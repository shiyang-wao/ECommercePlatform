package com.sy.feature_user.ui.address;

import com.sy.feature_user.api.UserApiServiceProvider;
import com.sy.libbase.bean.ResAddress;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.manager.UserManager;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import java.util.List;

import retrofit2.Call;

public class AddressModel {

    public void getAddress(IRequestCallback<List<ResAddress>> callback){
        String token = UserManager.getInstance().getToken();
        Call<ResBase<List<ResAddress>>> call = UserApiServiceProvider.getApiService().getAddress(token);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<List<ResAddress>>>() {
            @Override
            public void onSuccess(ResBase<List<ResAddress>> result) {
                callback.onLoadFinish(result.getData());
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
