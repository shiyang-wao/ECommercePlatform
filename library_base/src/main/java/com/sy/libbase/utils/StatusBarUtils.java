package com.sy.libbase.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 状态栏专用的工具类
 */
public class StatusBarUtils {
    /**
     * 把根布局加上状态栏的高度
     * @param rootView
     */
    public static void addStatusBarHeightRootView(View rootView){
        //为根布局设置padding值
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * 把对应的view加上状态栏的高度
     * @param rootView
     */
    public static void addStatusBarHeightView(View rootView,View...views){

        AtomicBoolean isNeedAddHeight= new AtomicBoolean(true); //默认需要添加高度

        //为根布局设置padding值
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            //该监听窗口发生变化时就会触发，
            //比如登陆页面的顶部控件调用该方法增加margin以实现沉浸式布局，可当EditText需要输入数据打开输入法时，
            //窗口发生变化，该监听里的方法会多次触发，导致顶部控件margin值不断增加，在这里控制只触发一次
            if (!isNeedAddHeight.get()){
                return insets;
            }

            for (int i = 0; i < views.length; i++) {
                View view = views[i];
                //获取到布局管理器后需要强转一下，LayoutParams是没有margin的
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)view.getLayoutParams();
                int topMargin = layoutParams.topMargin;
                layoutParams.setMargins(0,topMargin+systemBars.top,0,0);
                view.setLayoutParams(layoutParams);
            }
            isNeedAddHeight.set(false);
            return insets;
        });
    }

    /**
     * 开启沉浸式效果
     * @param activity
     */
    public static void setImmerseStatusBar(Activity activity) {
        //先把状态栏的颜色设置为透明色
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

        View decorView = activity.getWindow().getDecorView();
        int flags=View.SYSTEM_UI_FLAG_LAYOUT_STABLE| //稳定布局
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |//内容扩展到状态栏
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //指定状态栏图标深色模式
        decorView.setSystemUiVisibility(flags);
    }
}
