package com.sy.feature_user.ui.address;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.libbase.bean.ResAddress;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;

import java.util.List;

public class AddressViewModel extends BaseViewModel {
    private static final String TAG = "AddressViewModel";
    private final AddressModel mModel;
    private MutableLiveData<List<ResAddress>> mAddressDatas=new MutableLiveData<>();//收获地址列表

    public AddressViewModel() {
        mModel = new AddressModel();
    }

    public void getAddress(){
        mModel.getAddress(new IRequestCallback<List<ResAddress>>() {
            @Override
            public void onLoadFinish(List<ResAddress> datas) {
                mAddressDatas.setValue(datas);
                Log.i(TAG, "onLoadFinish: 收货地址内容"+datas.get(0).getMobile());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }

    public MutableLiveData<List<ResAddress>> getAddressDatas() {
        return mAddressDatas;
    }
}
