package com.sy.feature_shoppingcart.bean;

import java.util.List;

public class ReqOrderCal {

    /**
     * order_type : goods
     * goods_list : [{"goods_id":12,"goods_num":1,"goods_sku_price_id":20}]
     * from : cart
     * address_id : 38
     */

    private String order_type;
    private String from;
    private Integer address_id;
    private List<GoodsListBean> goods_list;

    public ReqOrderCal(String order_type, String from, List<GoodsListBean> goods_list) {
        this.order_type = order_type;
        this.from = from;
        this.goods_list = goods_list;
    }

    public ReqOrderCal(String order_type, String from, int address_id, List<GoodsListBean> goods_list) {
        this.order_type = order_type;
        this.from = from;
        this.address_id = address_id;
        this.goods_list = goods_list;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * goods_id : 12
         * goods_num : 1
         * goods_sku_price_id : 20
         */

        private int goods_id;
        private int goods_num;
        private int goods_sku_price_id;

        public GoodsListBean(int goods_id, int goods_num,int goods_sku_price_id) {
            this.goods_id = goods_id;
            this.goods_num = goods_num;
            this.goods_sku_price_id=goods_sku_price_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getGoods_sku_price_id() {
            return goods_sku_price_id;
        }

        public void setGoods_sku_price_id(int goods_sku_price_id) {
            this.goods_sku_price_id = goods_sku_price_id;
        }
    }
}
