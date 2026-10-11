package com.sy.feature_shoppingcart.bean;

public class ResUpdateCart {

    /**
     * id : 227
     * goods_id : 16
     * user_id : 18
     * goods_sku_price_id : 43
     * goods_num : 2
     * snapshot_price : 89.00
     * createtime : 2025-09-30 21:28:29
     * updatetime : 2025-09-30 21:52:32
     */

    private int id;
    private int goods_id;
    private int user_id;
    private int goods_sku_price_id;
    private String goods_num;
    private String snapshot_price;
    private String createtime;
    private String updatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGoods_sku_price_id() {
        return goods_sku_price_id;
    }

    public void setGoods_sku_price_id(int goods_sku_price_id) {
        this.goods_sku_price_id = goods_sku_price_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getSnapshot_price() {
        return snapshot_price;
    }

    public void setSnapshot_price(String snapshot_price) {
        this.snapshot_price = snapshot_price;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
