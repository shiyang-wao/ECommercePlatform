package com.sy.libbase.eventbus;

import com.sy.libbase.bean.ResAddress;

import org.greenrobot.eventbus.EventBus;

/**
 * 和 EventBus一起的， 用 EventBus发送这里定义的事件给别的页面
 */
public class MessageEvent {
    /**
     * 登陆状态变更的事件 登录成功、退出
     */
    public static class LoginStatusEvent {
        private boolean isLogin; //是否登录

        public LoginStatusEvent(boolean isLogin) {
            this.isLogin = isLogin;
        }

        public boolean isLogin() {
            return isLogin;
        }

        /**
         * 发送一个是否登陆的状态
         *
         * 在这里将发送事件封装了一下
         * @param isLogin
         */
        public static void postLoginEvent(boolean isLogin) {
            //postSticky:发送粘性事件
            //使用粘性事件发送消息，确保订阅者在活跃的时候再处理消息
            EventBus.getDefault().postSticky(new LoginStatusEvent(isLogin));
        }
    }

    /**
     * 选中收货地址事件
     * 用户选中后，要向上一个页面也就是确认订单页面传一个收货地址的信息
     */
    public static class SelectAddressEvent {

        private ResAddress selectAddress;
        public SelectAddressEvent(ResAddress selectAddress) {
            this.selectAddress=selectAddress;
        }

        /**
         * 发送一个选中收货地址的事件
         */
        public static void postSelectAddressEvent(ResAddress selectAddress) {
            //postSticky:发送粘性事件
            //使用粘性事件发送消息，确保订阅者在活跃的时候再处理消息
            EventBus.getDefault().postSticky(new SelectAddressEvent(selectAddress));
        }

        public ResAddress getSelectAddress() {
            return selectAddress;
        }
    }
}
