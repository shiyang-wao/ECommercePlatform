package com.sy.feature_shoppingcart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.sy.feature_shoppingcart.bean.ReqOrderCal;
import com.sy.feature_shoppingcart.bean.ResCartList;
import com.sy.feature_shoppingcart.bean.ResOrderCal;
import com.sy.feature_shoppingcart.databinding.ItemConfirmGoodsBinding;
import com.sy.feature_shoppingcart.databinding.ItemShopcartBinding;
import com.sy.feature_shoppingcart.ui.shopcart.ShopcartViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfirmOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ConfirmOrderAdapter";
    private List<ResOrderCal.GoodsListBean> mDatas;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemConfirmGoodsBinding binding = ItemConfirmGoodsBinding.inflate(inflater, parent, false);
        return new ConfirmOrderHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResOrderCal.GoodsListBean goodsListBean = mDatas.get(position);
        ConfirmOrderHolder viewhoder = (ConfirmOrderHolder) holder;
        ItemConfirmGoodsBinding binding = viewhoder.binding;

        // 1. 绑定商品图片（用Glide加载网络图片，需添加Glide依赖）
        String imageUrl = goodsListBean.getGoods().getImage(); // 商品主图
        String Url = "https://shop.fzqq.fun" + imageUrl;
        binding.setUrl(Url);

        binding.tvDistribution.setText(goodsListBean.getDispatch_type_text());
        binding.tvTitle.setText(goodsListBean.getGoods().getTitle());

        //拼接规格显示文本
        List<String> goodsSkuText = goodsListBean.getCurrent_sku_price().getGoods_sku_text();
        String sku = extracted(goodsSkuText);
        binding.tvSku.setText(sku);

        // 3. 绑定商品价格（用快照价格，无则用SKU价格）
        binding.tvPrice.setText(String.format("￥%s", goodsListBean.getCurrent_sku_price().getPrice()));

        // 4. 绑定商品数量
        binding.tvSum.setText("x "+goodsListBean.getGoods_num());

    }

    /**
     * 拼接规格显示文本  若没有返回空字符串
     * @param goodsSkuText
     * @return
     */
    private String extracted(List<String> goodsSkuText) {
        if (goodsSkuText != null && !goodsSkuText.isEmpty()) {
            StringBuilder skuText = new StringBuilder();
            for (String sku : goodsSkuText) {
                skuText.append(sku).append(",");
            }
            // 移除最后一个多余的逗号
            if (skuText.length() > 0) {
                skuText.setLength(skuText.length() - 1);
            }
            return skuText.toString();
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setDatas(List<ResOrderCal.GoodsListBean> resGoods) {
        mDatas = resGoods;
        notifyDataSetChanged();
    }

    // ViewHolder：用ViewBinding绑定Item布局
    public class ConfirmOrderHolder extends RecyclerView.ViewHolder {
        ItemConfirmGoodsBinding binding;

        public ConfirmOrderHolder(ItemConfirmGoodsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
