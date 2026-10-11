package com.sy.feature_user.ui.address;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sy.feature_user.R;
import com.sy.feature_user.adapter.AddressAdapter;
import com.sy.libbase.bean.ResAddress;
import com.sy.feature_user.databinding.ActivityAddressBinding;
import com.sy.libbase.base.BaseActivity;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.eventbus.MessageEvent;
import com.sy.libbase.utils.StatusBarUtils;

import java.util.List;

@Route(path = ARouterPath.User.ACTIVITY_ADDRESS)
public class AddressActivity extends BaseActivity<ActivityAddressBinding,AddressViewModel> {
    private static final String TAG = "AddressActivity";
    private AddressAdapter mAdapter;

    @Override
    protected AddressViewModel getViewModel() {
        return new ViewModelProvider(this).get(AddressViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_address;
    }

    @Override
    protected int getBindingVariableId() {
        return 0;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        mAdapter = new AddressAdapter();
        mDataBinding.rvAddress.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.rvAddress.setAdapter(mAdapter);
        // 添加分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mDataBinding.rvAddress.addItemDecoration(dividerItemDecoration);

        mAdapter.setmListener(new AddressAdapter.ItemHotsaleListener() {
            @Override
            public void onItemClick(ResAddress selectAddress) {
                Log.i(TAG, "onItemClick: 选中的收货地址id"+selectAddress.getId());
                MessageEvent.SelectAddressEvent.postSelectAddressEvent(selectAddress);
                finish();
            }
        });

        mDataBinding.addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterPath.User.ACTIVITY_ADD_ADDRESS).navigation();
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel.getAddress();

        mViewModel.getAddressDatas().observe(this, new Observer<List<ResAddress>>() {
            @Override
            public void onChanged(List<ResAddress> resAddresses) {
                mAdapter.setDatas(resAddresses);
            }
        });
    }
}