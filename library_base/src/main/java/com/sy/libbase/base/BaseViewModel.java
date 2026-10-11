package com.sy.libbase.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    private static final String TAG = "BaseViewModel";

    //错误码
    public MutableLiveData<Integer> mErrorCode = new MutableLiveData<>();

    //如果mToastText发生了变化，表示需要进行弹窗提示
    private MutableLiveData<String> mToastText =new MutableLiveData<>();

    //是否显示加载进度条样式
    private MutableLiveData<Boolean> mShowLoading =new MutableLiveData<>();

    //是否需要关闭当前页面
    private MutableLiveData<Boolean> mFinish=new MutableLiveData<>();
    private MutableLiveData<Boolean> mStartLoginAction=new MutableLiveData<>();
    public void startLogin(){
        mStartLoginAction.setValue(true);
    }

    public void showToast(String text){
        if (text==null||text.equals("")){
            return;
        }
        mToastText.setValue(text);
        //同一个viewModel在某些时候会被多个activity或fragment共用，其中的mToastText会被多次观测，弹窗会重复出现，置空重复观测的时候为空弹窗不会显示
        //事实证明，观察者的回调几乎同时发生，无法解决
        mToastText.setValue(null);
    }

    /**
     * 是否显示加载进度条
     * @return b
     */
    public void showLoading(boolean b){
        mShowLoading.setValue(b);
    }

    /**
     * 关闭页面
     */
    public void onFinishPage(){
        mFinish.setValue(true);
    }
    public MutableLiveData<Integer> getErrorCode() {
        return mErrorCode;
    }
    public MutableLiveData<String> getToastText() {
        return mToastText;
    }
    public MutableLiveData<Boolean> getShowLoad() {
        return mShowLoading;
    }
    public MutableLiveData<Boolean> getFinish() {
        return mFinish;
    }

    public MutableLiveData<Boolean> getmStartLoginAction() {
        return mStartLoginAction;
    }
}
