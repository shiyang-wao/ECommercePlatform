package com.sy.feature_user.ui.checkout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sy.feature_user.BR;
import com.sy.feature_user.R;
import com.sy.feature_user.bean.ReqOrderPay;
import com.sy.feature_user.bean.ResOrderPay;
import com.sy.feature_user.bean.ResUserInfo;
import com.sy.feature_user.databinding.ActivityCheckoutBinding;
import com.sy.libbase.base.BaseActivity;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.utils.StatusBarUtils;

@Route(path = ARouterPath.User.ACTIVITY_CHECKOUT)
public class CheckoutActivity extends BaseActivity<ActivityCheckoutBinding,CheckViewModel> {
    private static final String TAG = "CheckoutActivity";
    @Autowired(name = ARouterPath.Shopcart.KEY_PAY_FEE)
    String pauFee; // 要支付的金额
    @Autowired(name = ARouterPath.Shopcart.KEY_ORDER_SN)
    String oderSn; // 订单号应该是叫

    private CountDownTimer mDownTimer; //获取验证码的倒计时

    @Override
    protected CheckViewModel getViewModel() {
        return new ViewModelProvider(this).get(CheckViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_checkout;
    }

    @Override
    protected int getBindingVariableId() {
        return BR.checkviewmodel;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        showDownTimer();
    }

    @Override
    protected void initData() {
        mViewModel.getUserInfo();

        mViewModel.getPayFee().setValue("￥"+pauFee);
        Log.i(TAG, "initData: 支付金额："+mViewModel.getPayFee().getValue());

        mViewModel.mUserInfo.observe(this, new Observer<ResUserInfo>() {
            @Override
            public void onChanged(ResUserInfo resUserInfo) {
                Log.i(TAG, "onChanged: 余额："+resUserInfo.getMoney());
                mViewModel.getMoney().setValue("余额: "+resUserInfo.getMoney()+"元");
            }
        });

        mDataBinding.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mViewModel.getIsBalancePay().setValue(b);
            }
        });
        mDataBinding.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewModel.getIsBalancePay().getValue()){
                    ReqOrderPay reqOrderPay = new ReqOrderPay(oderSn,"money");
                    mViewModel.requestOrderPay(reqOrderPay);
                }else {
                    mViewModel.showToast("请选择余额支付！");
                }
            }
        });
    }

    /**
     * 显示倒计时
     */
    private void showDownTimer() {
        //倒计时
        if (mDownTimer!=null){
            mDownTimer.cancel(); //防止重复点击时，未停止之前的倒计时
        }
        //处理倒计时的显示
        mDownTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                //每1秒会触发这个方法 把毫秒转成秒
                int totalSeconds = (int)(l/1000);

                // 计算小时、分钟、秒
                int hours = totalSeconds / 3600;
                int minutes = (totalSeconds % 3600) / 60;
                int seconds = totalSeconds % 60;

                // 格式化为两位数的字符串
                String timeText = String.format("剩余支付时间 %02d:%02d:%02d", hours, minutes, seconds);
                mViewModel.getCountDownText().setValue(timeText);
            }

            @Override
            public void onFinish() {
                //倒计时完成后
                mDataBinding.pay.setBackgroundColor(ContextCompat.getColor(CheckoutActivity.this, com.sy.libbase.R.color.ff8d8d8d));
                mDataBinding.pay.setText("支付已过期");
            }
        }.start();
    }
}