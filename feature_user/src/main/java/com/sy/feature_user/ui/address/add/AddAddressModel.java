package com.sy.feature_user.ui.address.add;

import com.sy.feature_user.api.UserApiServiceProvider;
import com.sy.feature_user.bean.ReqAddAddress;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.manager.UserManager;
import com.sy.network.ApiCall;
import com.sy.network.bean.ResBase;

import retrofit2.Call;

public class AddAddressModel {

    public void addAddress(ReqAddAddress addAddress, IRequestCallback<ResBase<Void>> callback){
        String token = UserManager.getInstance().getToken();
        Call<ResBase<Void>> call = UserApiServiceProvider.getApiService().AddAddress(token, addAddress);
        ApiCall.enqueue(call, new ApiCall.ApiCallback<ResBase<Void>>() {
            @Override
            public void onSuccess(ResBase<Void> result) {
                callback.onLoadFinish(result);
            }

            @Override
            public void onError(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }
}
