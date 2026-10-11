package com.sy.libbase.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sy.libbase.utils.StatusBarUtils;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected VM mViewModel;
    protected V mDataBinding;
    private ProgressBar mProgressBar;

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initDataBinding();

//        //开启沉浸式状态栏
//        EdgeToEdge.enable(this);
//        //mDataBinding.getRoot()获取根布局
//        ViewCompat.setOnApplyWindowInsetsListener(mDataBinding.getRoot(), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //开启沉浸式效果
        StatusBarUtils.setImmerseStatusBar(this);

        ARouter.getInstance().inject(this); //让ARouter框架自动注入通过@Autowired注解标记的字段

        initView();
        initData();

        if (mDataBinding.getRoot() instanceof ConstraintLayout){
            initProgressBar();
        }
    }

    /**
     * 初始化加载进度条样式
     */
    private void initProgressBar() {
        mProgressBar = new ProgressBar(this);
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

    private void initDataBinding() {
        //最后创建出来的DataBinding类型与关联的xmlm布局的名字一一对应
        //若传过来的布局是R.layout.activity_login，则类型为ActivityLoginBinding
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        mDataBinding.setLifecycleOwner(this);
        //在父类中使用viewmodel在xml中的变量名，关联具体的mViewModel，效果等同于mDataBinding.setViewModel(mViewModel);
        mDataBinding.setVariable(getBindingVariableId(), mViewModel);
        //关联完mViewModel后，实时更新数据
        mDataBinding.executePendingBindings();
    }

    private void initViewModel() {
        //从子类获取到的ViewModel，，赋值给mViewModel
        mViewModel = getViewModel();

        if (mViewModel != null) {
            //控制是否显示Toast弹窗
            mViewModel.getToastText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String text) {
                    if (text != null && !text.isEmpty()) {
                        Log.i(TAG, "onChanged: "+text);
                        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
                    }

                }
            });
            //控制加载进度条是否显示
            mViewModel.getShowLoad().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean show) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
            //观察是否关闭页面
            mViewModel.getFinish().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean finish) {
                    if (finish) {
                        finish();//关闭页面
                    }
                }
            });
        }
    }

    protected abstract VM getViewModel();

    protected abstract int getLayoutResId();

    protected abstract int getBindingVariableId();

    protected abstract void initView();//如果子类需要做一些视图上的初始化

    protected abstract void initData();//如果子类需要做一些数据上的初始化


}
