package com.sy.feature_user.ui.user;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sy.feature_user.R;
import com.sy.feature_user.databinding.LayoutFragmentUserBinding;
import com.sy.libbase.base.BaseFragment;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@Route(path = ARouterPath.User.FRAGMENT_USER)
public class UserFragemnt extends BaseFragment<LayoutFragmentUserBinding,UserViewModel> {
    private static final String TAG = "UserFragemnt";
    @Override
    protected UserViewModel getViewModel() {
        return new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_fragment_user;
    }

    @Override
    protected int getBindingVariableId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
