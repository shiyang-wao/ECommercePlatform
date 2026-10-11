package com.sy.ecp;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sy.ecp.databinding.ActivityMainBinding;
import com.sy.libbase.base.BaseActivity;
import com.sy.libbase.config.ARouterPath;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {
    private static final String TAG = "MainActivity";

    @Override
    protected MainViewModel getViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getBindingVariableId() {
        return 0;
    }

    @Override
    protected void initView() {
        Fragment homeFragment = (Fragment)ARouter.getInstance().build(ARouterPath.Home.FRAGMENT_HOME).navigation();
        Fragment categoryFragment = (Fragment)ARouter.getInstance().build(ARouterPath.Category.FRAGMENT_CATEGORY).navigation();
        Fragment shopcartFragment = (Fragment)ARouter.getInstance().build(ARouterPath.Shopcart.FRAGMENT_SHOPCART).navigation();
        Fragment userFragment = (Fragment)ARouter.getInstance().build(ARouterPath.User.FRAGMENT_USER).navigation();
        replaceFragment(homeFragment);
        mDataBinding.rbBottomNavigation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.rb_home){
                    replaceFragment(homeFragment);
                } else if (checkedId==R.id.rb_category) {
                    replaceFragment(categoryFragment);
                }else if (checkedId==R.id.rb_shopcart) {
                    replaceFragment(shopcartFragment);
                }else if (checkedId==R.id.rb_user) {
                    replaceFragment(userFragment);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void replaceFragment(Fragment repFragmrnt) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fcv, repFragmrnt).commit();
    }
}