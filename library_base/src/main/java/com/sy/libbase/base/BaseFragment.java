package com.sy.libbase.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sy.libbase.R;

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    protected VM mViewModel;
    protected V mDataBinding;
//    protected LayoutStatusViewBinding mlayoutStatusViewBinding;
    private ProgressBar mProgressBar;

    private static final String TAG = "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
        mDataBinding.setLifecycleOwner(this);
        VM viewModel = getViewModel();
        if (viewModel != null) {
            mViewModel = viewModel;
        }

        int bindingVariableId = getBindingVariableId();
        if (bindingVariableId != 0) {
            //在父类中使用viewmodel在xml中的变量名，关联具体的mViewModel，效果等同于mDataBinding.setViewModel(mViewModel);
            mDataBinding.setVariable(bindingVariableId, mViewModel);
            //关联完mViewModel后，实时更新数据
            mDataBinding.executePendingBindings();
        }

        ARouter.getInstance().inject(this);

        initView();
        initData();
//        initStatusView();
        initToast();
        initProgressBar();

        if (mViewModel!=null){
//            mViewModel.getmStartLoginAction().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
//                @Override
//                public void onChanged(Boolean b) {
//                    //值为true，表示需要打开登录页面
//                    if (b){
//                        ARouter.getInstance().build(ARouterPath.User.ACTIVITY_LOGIN).navigation();
//                    }
//                }
//            });
        }

        return mDataBinding.getRoot();

    }

    /**
     * 初始化加载进度条样式
     */
    private void initProgressBar() {
        mProgressBar = new ProgressBar(getContext());

        if (mDataBinding.getRoot() instanceof ConstraintLayout) {
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            mProgressBar.setLayoutParams(layoutParams);
            mProgressBar.setVisibility(View.GONE);//默认这个进度条不可见
            ConstraintLayout constraintLayout = (ConstraintLayout) mDataBinding.getRoot();
            constraintLayout.addView(mProgressBar);
        }
    }


    private void initToast() {
        if (mViewModel != null) {
            mViewModel.getToastText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String text) {
                    if (text != null && !text.isEmpty()){
                        Log.i(TAG, "onChanged: "+text);
                        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //控制加载进度条是否显示
            mViewModel.getShowLoad().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean show) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }

    }


    /**
     * 初始化状态错误页面，使用的时候只需要设置mViewModel里的errorcode值即可
     */
//    private void initStatusView() {
//        ViewGroup root = (ViewGroup) mDataBinding.getRoot();
//        //查找状态错误提示布局
//        mlayoutStatusViewBinding = DataBindingUtil.inflate(getLayoutInflater(),
//                R.layout.layout_status_view, null, false);
//
//        View statusViewRoot = mlayoutStatusViewBinding.getRoot();
//        statusViewRoot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        root.addView(statusViewRoot);
//        if (mViewModel != null) {
//            //getViewLifecycleOwner():通过这个方法获取当前视图生命周期的所有者，以免造成内存泄露
//            mViewModel.getErrorCode().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//                @Override
//                public void onChanged(Integer errorCode) {
//                    String content = "";
//                    switch (errorCode) {
//                        case ErrorStatusConfig.ERROR_STATUS_NETWORK_FAIL:
//                            content = "网络错误，请检查网络！";
//                            break;
//                        case ErrorStatusConfig.ERROR_STATUS_NOT_LOGIN:
//                            content = "请登陆后再进行操作！";
//                            break;
//                        case ErrorStatusConfig.ERROR_STATUS_EMPTY:
//                            content = "当前没有更多数据了！";
//                            break;
//                        case ErrorStatusConfig.ERROR_STATUS_SERVER_ERROR:
//                            content = "服务器异常！";
//                            break;
//                        case ErrorStatusConfig.ERROR_STATUS_NORMAL:
//                        default:
//                            content = "";
//                            break;
//                    }
//
//                    LayoutStatusViewBinding layoutStatusView = mlayoutStatusViewBinding;
//                    //errorCode错误码为不正常时，将layout_Status_View这个布局设为可见
//                    boolean isVisibility = errorCode != ErrorStatusConfig.ERROR_STATUS_NORMAL;
//                    layoutStatusView.clStatusView.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
//                    layoutStatusView.tvLable.setText(content);
//                }
//            });
//        }
//    }

    protected abstract VM getViewModel();

    protected abstract int getLayoutResId();

    protected abstract int getBindingVariableId();

    protected abstract void initView();//如果子类需要做一些视图上的初始化

    protected abstract void initData();//如果子类需要做一些数据上的初始化


}
