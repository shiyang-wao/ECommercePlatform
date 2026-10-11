package com.sy.libbase.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;

import me.jessyan.autosize.AutoSizeConfig;


/**
 * 当前工程中Application的基类
 */
public class BaseApplication extends Application {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;

        //BuildConfig.DEBUG表示在调试模式下
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        //初始化
        ARouter.init(this);
        //AndroidAutoSize的参数初始化             表示要去支持fragment
        AutoSizeConfig.getInstance().setCustomFragment(true);
    }

    /**
     * 使用Applicaion生成了一个全局可用的Context   全局可用是因为在这里它在base这个库里面，其他的模块都能引用
     * 注意不要滥用，否则会产生下面的问题
     * 1.把Applicaion当成是某个Activity上下文，与ui更新关联到一起，会报错
     * 2.更容易获取到context，会增加项目的耦合性
     * @return
     */
    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
