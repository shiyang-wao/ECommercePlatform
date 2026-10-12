package com.sy.feature_shoppingcart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.sy.feature_shoppingcart.bean.ResCartList;
import com.sy.feature_shoppingcart.databinding.ItemShopcartBinding;
import com.sy.feature_shoppingcart.ui.shopcart.ShopcartViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CartAdapter";
    private Context mContext;
    private List<ResCartList> mCartList;
    private ShopcartViewModel mViewModel;
    // 选中状态缓存（避免频繁读取LiveData）
    private Set<Integer> mSelectedIds = new HashSet<>();
    // CartAdapter 中修改监听逻辑
    private Set<Integer> mLastSelectedIds = new HashSet<>(); // 记录上一次选中状态
    private boolean mIsEditMode = false; // 添加编辑模式状态

    public CartAdapter(Context context, ShopcartViewModel viewModel) {
        this.mContext = context;
        this.mViewModel = viewModel;
        this.mCartList = new ArrayList<>();

        // 观察ViewModel的选中状态变化，更新UI
        mViewModel.getSelectedCartIds().observe((LifecycleOwner) context, newSelectedIds -> {
            if (newSelectedIds == null) return;

            // 1. 找出选中状态变化的CartId
            Set<Integer> changedIds = new HashSet<>();
            // 新增的选中项
            for (int id : newSelectedIds) {
                if (!mLastSelectedIds.contains(id)) {
                    changedIds.add(id);
                }
            }
            // 取消的选中项
            for (int id : mLastSelectedIds) {
                if (!newSelectedIds.contains(id)) {
                    changedIds.add(id);
                }
            }

            // 2. 只刷新变化的Item
            if (!changedIds.isEmpty()) {
                for (int i = 0; i < mCartList.size(); i++) {
                    int cartId = mCartList.get(i).getId();
                    if (changedIds.contains(cartId)) {
                        notifyItemChanged(i); // 局部刷新，仅更新该位置
                    }
                }
            }

            // 3. 更新缓存的选中状态
            mLastSelectedIds.clear();
            mLastSelectedIds.addAll(newSelectedIds);
            mSelectedIds = newSelectedIds;
        });

        // 新增：观察编辑模式变化
        mViewModel.getIsEditMode().observe((LifecycleOwner) context, isEditMode -> {
            if (isEditMode != mIsEditMode) {
                mIsEditMode = isEditMode != null ? isEditMode : false;
                notifyDataSetChanged(); // 编辑模式切换时刷新所有item
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemShopcartBinding binding = ItemShopcartBinding.inflate(inflater, parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResCartList cart = mCartList.get(position);
        CartViewHolder viewhoder = (CartViewHolder) holder;
        ItemShopcartBinding binding = viewhoder.binding;

        // 1. 绑定商品图片（用Glide加载网络图片，需添加Glide依赖）
        String imageUrl = cart.getGoods().getImage(); // 商品主图
        String Url = "https://shop.example.com" + imageUrl;
        binding.setUrl(Url);
        binding.setData(cart);

        // 3. 绑定商品价格（用快照价格，无则用SKU价格）
        String priceStr = cart.getSnapshot_price() != null
                ? cart.getSnapshot_price()
                : cart.getSku_price().getPrice();
        binding.tvPrice.setText(String.format("￥%s", priceStr));

        // 4. 绑定商品数量
        binding.tvSelectSum.setText(String.valueOf(cart.getGoods_num()));

        //拼接规格显示文本
        List<String> goodsSkuText = cart.getSku_price().getGoods_sku_text();
        String sku = extracted(goodsSkuText);
        binding.tvSku.setText(sku);

        // 关键：先移除监听器，避免设置状态时触发回调
        binding.cbCheck.setOnCheckedChangeListener(null);

        // 设置选中状态（此时不会触发回调）
        boolean isSelected = mSelectedIds.contains(cart.getId());
        binding.cbCheck.setChecked(isSelected);

        // 再重新设置监听器
        binding.cbCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mViewModel.toggleItemSelected(cart.getId(), isChecked);
        });

        // 6. Checkbox点击事件：通知ViewModel切换选中状态
        binding.cbCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mViewModel.toggleItemSelected(cart.getId(), isChecked);
        });

        // 7. 减少数量按钮点击事件
        binding.ivSubtract.setOnClickListener(v -> {
            if (!mIsEditMode) { // 只有在非编辑模式下才能增减数量
                mViewModel.updateGoodsNum(cart, false);  // false=减少
            }
        });

        // 8. 增加数量按钮点击事件
        binding.ivAdd.setOnClickListener(v -> {
            if (!mIsEditMode) { // 只有在非编辑模式下才能增减数量
                mViewModel.updateGoodsNum(cart, true);  // true=增加
            }
        });

        // 关键修改：使用缓存的编辑模式状态
        binding.ivAdd.setVisibility(mIsEditMode ? View.GONE : View.VISIBLE);
        binding.ivSubtract.setVisibility(mIsEditMode ? View.GONE : View.VISIBLE);
        binding.tvSelectSum.setVisibility(mIsEditMode ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mCartList != null ? mCartList.size() : 0;
    }

    public void setDatas(List<ResCartList> resCarts) {
        if (resCarts != null) {
            mCartList = resCarts;
            Log.i(TAG, "setDatas: 购物车数据大小: " + mCartList.size());
        } else {
            mCartList = new ArrayList<>();
            Log.i(TAG, "setDatas: 购物车数据为null");
        }

        // 添加调试日志
        for (ResCartList item : mCartList) {
            Log.i(TAG, "setDatas: 商品ID=" + item.getId() + ", 名称=" + item.getGoods().getTitle());
        }

        notifyDataSetChanged();

//        if (!resCarts.isEmpty()) {
//            mCartList = resCarts;
//            Log.i(TAG, "onChanged: 分类数据" + mCartList.isEmpty() + mCartList.size());
//        }
//        //改变适配器的数据一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定一定要刷新
//        notifyDataSetChanged();
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

    // ViewHolder：用ViewBinding绑定Item布局
    public class CartViewHolder extends RecyclerView.ViewHolder {
        ItemShopcartBinding binding;

        public CartViewHolder(ItemShopcartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
