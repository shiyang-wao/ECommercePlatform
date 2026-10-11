package com.sy.libbase.dialog;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sy.libbase.R;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;


import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.databinding.LayoutDialogLoginBinding;

import java.util.ArrayList;
import java.util.List;


public class LoginDialog extends DialogFragment {

    private static final String TAG = "LoginDialog";
    private final Callback mCallback;
    private LayoutDialogLoginBinding mBinding;
    private boolean mCheckAgreement =false; //是否同意协议
    public LoginDialog(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 2. 提前获取Window并设置宽度（在show()之前）
        Window window = getDialog().getWindow();
        if (window != null) {
            // 1. 获取屏幕宽度（兼容所有版本）
            DisplayMetrics displayMetrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenWidth = displayMetrics.widthPixels;

            WindowManager.LayoutParams lp = window.getAttributes();
            // 设置宽度为屏幕宽度（与OnShow中效果一致，但提前执行）
            lp.width = screenWidth;
            // 其他属性（如位置、高度等）也可以在这里提前设置
            lp.gravity = Gravity.BOTTOM; // 底部显示
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.getDecorView().setPadding(0, 0, 0, dp2px(10)); // 清除左右padding
            // 应用属性（提前生效，无需等待OnShow）
            window.setAttributes(lp);
        }
    }

    /**
     * 将获取Dialog实例和显示弹窗一起封装起来，外部只需要一个方法就可以显示弹窗
     * @param activity
     * @param callback
     */
    public static void showDialog(FragmentActivity activity, Callback callback){

        Log.i("LoginDialog", "showDialog: Activity实例=" + activity + ", hashCode=" + activity.hashCode());
        Log.i("LoginDialog", "showDialog: Activity是否finishing=" + activity.isFinishing());

        LoginDialog goodsSelectDialog = newInstance(callback);
        goodsSelectDialog.show(activity.getSupportFragmentManager(),"LoginDialog");

    }

    /**
     * 可以快速获得一个GoodsSelectDialog的实例
     * 每个fragment都可以打new快捷生成一个newInstance方法
     * @return
     */
    private static LoginDialog newInstance(Callback callback) {
        LoginDialog fragment = new LoginDialog(callback);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // 初始化ViewBinding
        mBinding = LayoutDialogLoginBinding.inflate(getLayoutInflater());
        // 绑定商品数据到XML
        mBinding.executePendingBindings(); // 立即执行数据绑定，避免UI延迟

        // 1. 初始化弹窗样式（全屏、无标题、背景透明）
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FullScreenDialogStyle);
        //布局关联dialog
        builder.setView(mBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true); // 点击外部取消
        setCancelable(true);

        //初始化"请阅读并同意《用户协议》和《隐私政策》"
        initAgreementText();

        //初始化点击事件
        initClick();

        // 6. 弹窗取消监听
        dialog.setOnCancelListener(dialogInterface -> {
            if (mCallback != null) {
                mCallback.onCancel();
            }
        });

        return dialog;
    }

    /**
     * 初始化点击事件
     */
    private void initClick() {
        mBinding.tvLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckAgreement=mBinding.cbAgreen.isChecked();
                if (mCheckAgreement){
                    String account = mBinding.etAccount.getText().toString();
                    String password = mBinding.etPassword.getText().toString();
                    if (!account.isEmpty()&&!password.isEmpty()){
                        mCallback.onLogin(account,password);
                    }else {
                        Toast.makeText(getContext(), "请输入账户和密码！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "请勾选协议！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initAgreementText() {
        String string = "请阅读并同意《用户协议》和《隐私政策》";
        //借助SpannableString包装处理字符串内容
        SpannableString spannableString = new SpannableString(string);
        //添加点击事件
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.i(TAG, "onClick: 准备跳到用户协议");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);

                ds.setColor(0xFFF6380D);
                ds.setUnderlineText(false); //不加下划线
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.i(TAG, "onClick: 准备跳到隐私政策");
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);

                ds.setColor(0xFFF6380D);
                ds.setUnderlineText(false); //不加下划线
            }
        };

        spannableString.setSpan(clickableSpan1, 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan2, 14, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBinding.cbAgreen.setText(spannableString);
        // 让 cbAgreen 控件中通过 ClickableSpan 设置的可点击文本片段能够真正响应点击。
        // 如果没有这行代码，即使文本样式被 ClickableSpan 定义，点击也不会触发任何逻辑。
        mBinding.cbAgreen.setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * dp转px（避免不同屏幕尺寸适配问题）
     */
    private int dp2px(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 弹窗销毁时释放资源
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null; // 释放ViewBinding，避免内存泄漏
    }

    /**
     * 关闭登录弹窗
     */
    public static void dismissDialog(FragmentActivity activity) {
        if (activity == null || activity.isFinishing()) return;

        LoginDialog loginDialog = (LoginDialog) activity.getSupportFragmentManager()
                .findFragmentByTag("LoginDialog");

        if (loginDialog != null && loginDialog.isVisible()) {
            loginDialog.dismiss();
        }
    }

    public interface Callback{
        // 确认选择（加入购物车/立即购买共用，用type区分）
        void onLogin(String account,String password);
        // 取消弹窗
        void onCancel();
    }
}
