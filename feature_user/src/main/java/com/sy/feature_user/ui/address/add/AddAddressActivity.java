package com.sy.feature_user.ui.address.add;

import static com.sy.libbase.base.BaseApplication.getContext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.gzuliyujiang.wheelpicker.AddressPicker;
import com.github.gzuliyujiang.wheelpicker.annotation.AddressMode;
import com.github.gzuliyujiang.wheelpicker.contract.OnAddressPickedListener;
import com.github.gzuliyujiang.wheelpicker.entity.CityEntity;
import com.github.gzuliyujiang.wheelpicker.entity.CountyEntity;
import com.github.gzuliyujiang.wheelpicker.entity.ProvinceEntity;
import com.sy.feature_user.BR;
import com.sy.feature_user.R;
import com.sy.feature_user.databinding.ActivityAddAddressBinding;
import com.sy.libbase.base.BaseActivity;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.utils.StatusBarUtils;

@Route(path = ARouterPath.User.ACTIVITY_ADD_ADDRESS)
public class AddAddressActivity extends BaseActivity<ActivityAddAddressBinding, AddAddressViewModel> {

    private static final String TAG = "AddAddressActivity";

    @Override
    protected AddAddressViewModel getViewModel() {
        return new ViewModelProvider(this).get(AddAddressViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected int getBindingVariableId() {
        return BR.addViewModel;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        mDataBinding.etDetailedAddress.setSingleLine(false);  // 重要：设置为非单行

        //省市区选择弹窗
        AddressPicker picker = new AddressPicker(this);
        picker.setTitle("选择区域");
        picker.setAddressMode(AddressMode.PROVINCE_CITY_COUNTY);
        picker.setOnAddressPickedListener(new OnAddressPickedListener() {
            @Override
            public void onAddressPicked(ProvinceEntity province, CityEntity city, CountyEntity county) {
                mViewModel.getProvinceName().setValue(province.getName());
                mViewModel.getCityName().setValue(city.getName());
                mViewModel.getDistrictName().setValue(county.getName());

                mDataBinding.tvProvinceCityRes.setText(province.getName() + "-" + city.getName() + "-" + county.getName());
                mDataBinding.tvProvinceCityRes.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                Log.i(TAG, "onAddressPicked: " + province.getName());
            }
        });
        mDataBinding.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show();
            }
        });

    }

    @Override
    protected void initData() {
        mDataBinding.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mViewModel.getIsDefault().setValue(true);
                }else {
                    mViewModel.getIsDefault().setValue(false);
                }
            }
        });

        mDataBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.addAddress();
            }
        });
    }
}