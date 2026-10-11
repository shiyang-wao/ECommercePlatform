package com.sy.feature_user.ui.address.add;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sy.feature_user.bean.ReqAddAddress;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;
import com.sy.network.bean.ResBase;

public class AddAddressViewModel extends BaseViewModel {
    private static final String TAG = "AddAddressViewModel";
    private final AddAddressModel mModel;
    private MutableLiveData<Boolean> mIsDefault=new MutableLiveData<>(false); //是默认地址？
    private MutableLiveData<String> mConsignee=new MutableLiveData<>(); //收货人
    private MutableLiveData<String> mMobile=new MutableLiveData<>();
    private MutableLiveData<String> mProvinceName=new MutableLiveData<>();
    private MutableLiveData<String> mCityName=new MutableLiveData<>();
    private MutableLiveData<String> mDistrictName=new MutableLiveData<>(); //地区名称
    private MutableLiveData<String> mAddress=new MutableLiveData<>();  //详细地址

    public AddAddressViewModel() {
        mModel = new AddAddressModel();
    }

    /**
     * 添加一个收货地址
     */
    public void addAddress(){
        ReqAddAddress reqAddAddress = new ReqAddAddress(mConsignee.getValue(), mMobile.getValue(),
                mProvinceName.getValue(),mCityName.getValue(),mDistrictName.getValue(),
                mAddress.getValue(),mIsDefault.getValue());

        Log.i(TAG, "addAddress: 请求体"+reqAddAddress);
        mModel.addAddress(reqAddAddress, new IRequestCallback<ResBase<Void>>() {
            @Override
            public void onLoadFinish(ResBase<Void> datas) {
                showToast(datas.getMsg());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: "+message);
            }
        });
    }

    public MutableLiveData<Boolean> getIsDefault() {
        return mIsDefault;
    }

    public MutableLiveData<String> getConsignee() {
        return mConsignee;
    }

    public MutableLiveData<String> getMobile() {
        return mMobile;
    }

    public MutableLiveData<String> getProvinceName() {
        return mProvinceName;
    }

    public MutableLiveData<String> getCityName() {
        return mCityName;
    }

    public MutableLiveData<String> getDistrictName() {
        return mDistrictName;
    }

    public MutableLiveData<String> getAddress() {
        return mAddress;
    }
}
