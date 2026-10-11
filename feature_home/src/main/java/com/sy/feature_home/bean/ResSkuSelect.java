package com.sy.feature_home.bean;

import java.util.List;

public class ResSkuSelect {
    private int goodsId;              // 商品ID（如24）     update购物车会用
    private String title;             // 商品名称
    private String image;             // 商品图片
    private String selectedSpec;      // 选中的规格组合（如“粉色/M”）
    private int selectedStock;        // 选中规格的库存
    private String selectedPrice;     // 选中规格的售价
    private int selectSum;            // 该次选中规格的数量   update购物车会用
    private int goods_sku_price_id;   // 商品规格价格id      update购物车会用

    private List<ResGoodsMoreDetail.SkusBean.SkuOption> selectedOptions; // 选中的具体规格项（便于后续处理）

    public ResSkuSelect(int goodsId, String title, String image, String selectedSpec, String selectedPrice, int selectSum,int goods_sku_price_id) {
        this.goodsId = goodsId;
        this.title = title;
        this.image = image;
        this.selectedSpec = selectedSpec;
        this.selectedPrice = selectedPrice;
        this.selectSum = selectSum;
        this.goods_sku_price_id=goods_sku_price_id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSelectedSpec() {
        return selectedSpec;
    }

    public void setSelectedSpec(String selectedSpec) {
        this.selectedSpec = selectedSpec;
    }

    public int getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(int selectedStock) {
        this.selectedStock = selectedStock;
    }

    public String getSelectedPrice() {
        return selectedPrice;
    }

    public void setSelectedPrice(String selectedPrice) {
        this.selectedPrice = selectedPrice;
    }

    public int getSelectSum() {
        return selectSum;
    }

    public void setSelectSum(int selectSum) {
        this.selectSum = selectSum;
    }

    public List<ResGoodsMoreDetail.SkusBean.SkuOption> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<ResGoodsMoreDetail.SkusBean.SkuOption> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public int getGoods_sku_price_id() {
        return goods_sku_price_id;
    }

    public void setGoods_sku_price_id(int goods_sku_price_id) {
        this.goods_sku_price_id = goods_sku_price_id;
    }

    @Override
    public String toString() {
        return "ResSkuSelect{" +
                "goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", selectedSpec='" + selectedSpec + '\'' +
                ", selectedStock=" + selectedStock +
                ", selectedPrice='" + selectedPrice + '\'' +
                ", selectSum=" + selectSum +
                ", selectedOptions=" + selectedOptions +
                '}';
    }
}
