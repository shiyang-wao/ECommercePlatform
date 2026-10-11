package com.sy.feature_shoppingcart.bean;

public class ReqUpdateCart {
    private int goods_id;
    private int goods_num;
    private int goods_sku_price_id;

    private String type;  //cover是覆盖已有的购物车item, 类型inc增加，dec减少

    public ReqUpdateCart(int goods_id, int goods_num, int goods_sku_price_id, String type) {
        this.goods_id = goods_id;
        this.goods_num = goods_num;
        this.goods_sku_price_id = goods_sku_price_id;
        this.type = type;
    }
}
