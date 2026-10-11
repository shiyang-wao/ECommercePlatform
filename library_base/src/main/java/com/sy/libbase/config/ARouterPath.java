package com.sy.libbase.config;

public class ARouterPath {
    public static class Home {
        private static final String HOME = "/home";
        //对应MainActivity的路径
        public static final String FRAGMENT_HOME = HOME + "/HomeFragment";
        public static final String ACTIVITY_GOODS_DETAIL = HOME + "/GoodsDetailActivity";
        public static final String KEY_GOODS_ID = "KEY_GOODS_ID"; //跳转到商品详情页面，传商品id的key
        public static final String FRAGMENT_IMAGE = HOME + "/ImageFragment"; //商品详情页的图片页
        public static final String KEY_IMAGE_URL = "KEY_IMAGE_UR"; //跳转到图片页，传图片url的key
        public static final String KEY_IMAGE_POSITION = "KEY_IMAGE_POSITION"; //跳转到图片页，传图片当前位置的key，确定当前图片是哪一个fragment，为了处理点击事件
        public static final String FRAGMENT_IMAGE_DETAIL = HOME + "/ImageDetailFragment"; //跳转到图片详情页
    }

    public static class Category {
        private static final String CATEGORY = "/category";
        //对应MainActivity的路径
        public static final String FRAGMENT_CATEGORY = CATEGORY + "/CategoryFragment";
    }

    public static class Shopcart {
        private static final String SHOPCART = "/shopcart";
        //对应MainActivity的路径
        public static final String FRAGMENT_SHOPCART = SHOPCART + "/ShopcartFragment";
        public static final String SERVICE_SHOP_CART = SHOPCART + "/ShopCartServiceImpl";
        public static final String ACTIVITY_CONFIRM_ORDER = SHOPCART + "/ConfirmOrderActivity";
        public static final String KEY_CONFIRM_ORDER = "KEY_CONFIRM_ORDER";
        public static final String KEY_PAY_FEE = "KEY_PAY_FEE";
        public static final String KEY_ORDER_SN = "KEY_ORDER_SN";
    }

    public static class User {
        private static final String USER = "/user";
        //对应MainActivity的路径
        public static final String FRAGMENT_USER = USER + "/UserFragment";
        public static final String SERVICE_USER_LOGIN = USER + "/UserLoginServiceImpl";
        public static final String ACTIVITY_ADDRESS = USER + "/AddressActivity";
        public static final String ACTIVITY_ADD_ADDRESS = USER + "/AddAddressActivity";
        public static final String ACTIVITY_CHECKOUT = USER + "/CheckoutActivity";
    }
}
