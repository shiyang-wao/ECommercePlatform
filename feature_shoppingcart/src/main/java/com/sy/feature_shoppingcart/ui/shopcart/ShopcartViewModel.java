package com.sy.feature_shoppingcart.ui.shopcart;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.sy.feature_shoppingcart.bean.ResCartList;
import com.sy.feature_shoppingcart.bean.ResUpdateCart;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.base.IRequestCallback;
import com.sy.libbase.manager.UserManager;
import com.sy.network.bean.ResBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShopcartViewModel extends BaseViewModel {
    private static final String TAG = "ShopcartViewModel";
    private final ShopcartModel mModel;

    // 1. 核心可观察数据：购物车列表（所有UI操作依赖此数据）
    private MutableLiveData<List<ResCartList>> mCartList = new MutableLiveData<>(new ArrayList<>());
    // 2. 编辑模式状态（true=编辑模式，false=正常模式）
    private MutableLiveData<Boolean> isEditMode = new MutableLiveData<>(false);
    // 3. 选中的购物车ID列表（管理单个/全选状态）
    private MutableLiveData<Set<Integer>> selectedCartIds = new MutableLiveData<>(new HashSet<>());
    // 4. 合计金额（选中商品的总价格）
    private MutableLiveData<String> totalPrice = new MutableLiveData<>("¥0.00");
    // 5. 商品总数（顶部“共X件商品”）
    private MutableLiveData<Integer> mSumList = new MutableLiveData<>(0);
    // 6. 操作按钮可用性（去结算/删除：选中商品>0时可用）
    private MutableLiveData<Boolean> isOperationEnable = new MutableLiveData<>(false);
    // 7. 全选状态（选中数量=列表总数且列表非空时为true）
    private MutableLiveData<Boolean> isAllSelected = new MutableLiveData<>(false);

    public ShopcartViewModel() {
        mModel = new ShopcartModel();
        // 监听「购物车列表」和「选中ID列表」变化，自动更新合计、总数、全选状态
        observeCartAndSelectedData();
    }

    /**
     * 监听购物车列表和选中状态，同步更新衍生数据（合计、总数、全选、按钮可用性）
     */
    private void observeCartAndSelectedData() {
        // 合并监听：购物车列表 或 选中ID列表变化时触发
        MediatorLiveData<Object> combineLiveData = new MediatorLiveData<>();
        // 添加mCartList作为数据源，变化时触发更新
        combineLiveData.addSource(mCartList, value -> updateDerivedData());
        // 添加selectedCartIds作为数据源，变化时触发更新
        combineLiveData.addSource(selectedCartIds, value -> updateDerivedData());
        // 关键：必须观察combineLiveData才会触发回调（之前漏了这步）
        combineLiveData.observeForever(ignored -> {});
    }

    /**
     * 更新衍生数据（合计金额、商品总数、全选状态、按钮可用性）
     */
    private void updateDerivedData() {
        List<ResCartList> cartList = mCartList.getValue();
        Set<Integer> selectedIds = selectedCartIds.getValue();
        if (cartList == null || selectedIds == null) return;

        // 1. 更新商品总数（顶部“共X件商品”）
        mSumList.setValue(cartList.size());

        // 2. 计算合计金额（选中商品：数量 × 单价）
        double total = 0.0;
        for (ResCartList cart : cartList) {
            if (selectedIds.contains(cart.getId())) { // 只计算选中的商品
                // 单价：优先用快照价格snapshot_price，无则用sku价格
                String priceStr = cart.getSnapshot_price() != null ? cart.getSnapshot_price() : cart.getSku_price().getPrice();
                double price = Double.parseDouble(priceStr);
                int num = cart.getGoods_num();
                total += price * num;
            }
        }
        totalPrice.setValue(String.format("¥%.2f", total)); // 格式化为“¥XX.XX”

        // 3. 更新全选状态（选中数量=列表总数 且 列表非空）
        boolean allSelected = !cartList.isEmpty() && selectedIds.size() == cartList.size();
        isAllSelected.setValue(allSelected);

        // 4. 更新操作按钮可用性（选中数量>0时可用）
        isOperationEnable.setValue(selectedIds.size() > 0);
    }


    /**
     * 添加一条购物车数据，更新购物车
     *
     * @param goods_id
     * @param goods_num
     * @param goods_sku_price_id
     * @param type
     * @param callback
     */
    public void updateCart(int goods_id, int goods_num, int goods_sku_price_id, String type, IRequestCallback<ResBase<ResUpdateCart>> callback) {
        mModel.updateCart(goods_id, goods_num, goods_sku_price_id, type, new IRequestCallback<ResBase<ResUpdateCart>>() {
            @Override
            public void onLoadFinish(ResBase<ResUpdateCart> datas) {
                callback.onLoadFinish(datas);
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                callback.onLoadFail(errorCode, message);
            }
        });
    }

    /**
     * 获取购物车列表
     */
    public void getCartList() {
        UserManager instance = UserManager.getInstance();
        if (!instance.isLogin()) {
            showToast("未登录");
            return;
        }
        mModel.getCartList(new IRequestCallback<ResBase<List<ResCartList>>>() {
            @Override
            public void onLoadFinish(ResBase<List<ResCartList>> datas) {
                Log.i(TAG, "购物车列表大小: " + datas.getData().size()); // 确认数据不为空且大小正确

                if (datas.getData() != null) {
                    mCartList.setValue(datas.getData());
                    // 重置选中状态（重新获取列表后，选中状态清空）
                    selectedCartIds.setValue(new HashSet<>());
                } else {
                    showToast(datas.getMsg());
                }
                Log.i(TAG, "onLoadFinish: 购物车列表" + datas.getMsg());
            }

            @Override
            public void onLoadFail(int errorCode, String message) {
                Log.i(TAG, "onLoadFail: " + message);
            }
        });
    }

    // ------------------------------ 编辑模式相关 ------------------------------

    /**
     * 切换编辑模式（编辑 ↔ 取消）
     */
    public void toggleEditMode() {
        boolean currentMode = isEditMode.getValue() != null ? isEditMode.getValue() : false;
        isEditMode.setValue(!currentMode);
        // 切换模式时，清空选中状态（可选，根据需求决定是否保留）
//        selectedCartIds.setValue(new HashSet<>());
    }

    // ------------------------------ Checkbox选中相关 ------------------------------

    /**
     * 单个商品选中/取消选中
     *
     * @param cartId    购物车ID（ResCartList的id）
     * @param isChecked 是否选中
     */
    public void toggleItemSelected(int cartId, boolean isChecked) {
        Set<Integer> selectedIds = selectedCartIds.getValue();
        if (selectedIds == null) selectedIds = new HashSet<>();

        if (isChecked) {
            selectedIds.add(cartId);
        } else {
            selectedIds.remove(cartId);
        }
        selectedCartIds.setValue(selectedIds); // 触发LiveData更新
    }


    /**
     * 全选/取消全选
     *
     * @param isAllChecked 是否全选
     */
    public void toggleAllSelected(boolean isAllChecked) {
        List<ResCartList> cartList = mCartList.getValue();
        Set<Integer> selectedIds = new HashSet<>();
        if (cartList == null || cartList.isEmpty()) {
            selectedCartIds.setValue(selectedIds);
            return;
        }

        if (isAllChecked) {
            // 全选：添加所有购物车ID
            for (ResCartList cart : cartList) {
                selectedIds.add(cart.getId());
            }
        } else {
            // 取消全选：清空选中ID
            selectedIds.clear();
        }
        selectedCartIds.setValue(selectedIds);
    }

    // ------------------------------ 商品数量增减相关 ------------------------------

    /**
     * 增减商品数量（点击+/-按钮时调用）
     *
     * @param cart       当前商品
     * @param isIncrease 是否增加（true=+1，false=-1）
     */
    public void updateGoodsNum(ResCartList cart, boolean isIncrease) {
        int currentNum = cart.getGoods_num();
        int newNum = isIncrease ? currentNum + 1 : currentNum - 1;

        // 1. 数量减到0：删除该商品（调用删除接口）
        if (newNum <= 0) {
            deleteCartItem(String.valueOf(cart.getId())); // 单个删除，传购物车ID
            return;
        }

        // 2. 数量>0：更新数量（调用updateCart接口，type=cover）
        mModel.updateCart(
                cart.getGoods_id(),  // 商品ID
                newNum,              // 新数量
                cart.getGoods_sku_price_id(), // 规格ID
                "cover",             // type=cover（购物车内增减数量）
                new IRequestCallback<ResBase<ResUpdateCart>>() {
                    @Override
                    public void onLoadFinish(ResBase<ResUpdateCart> datas) {
                        // 更新成功：重新获取购物车列表（确保数据一致）
                        getCartList();
                        showToast(datas.getMsg());
                    }

                    @Override
                    public void onLoadFail(int errorCode, String message) {
                        showToast("数量更新失败：" + message);
                    }
                }
        );
    }


    // ------------------------------ 商品删除相关 ------------------------------

    /**
     * 单个删除商品（数量减到0时调用）
     *
     * @param cartId 购物车ID（单个）
     */
    private void deleteCartItem(String cartId) {
        mModel.deleteCart(
                cartId,
                new IRequestCallback<ResBase<Void>>() {
                    @Override
                    public void onLoadFinish(ResBase<Void> datas) {
                        // 删除成功后立即更新本地数据，不要等待重新获取列表
                        // 方法1：立即从当前列表中移除被删除的商品
                        List<ResCartList> currentList = mCartList.getValue();
                        if (currentList != null) {
                            List<ResCartList> newList = new ArrayList<>();
                            for (ResCartList item : currentList) {
                                if (item.getId() != Integer.parseInt(cartId)) {
                                    newList.add(item);
                                }
                            }
                            // 立即更新列表，不要等待网络请求
                            mCartList.setValue(newList);
                        }

                        showToast(datas.getMsg());

                        // 方法2：同时重新获取最新数据确保与服务端同步
                        getCartList();
                    }

                    @Override
                    public void onLoadFail(int errorCode, String message) {
                        showToast("删除失败：" + message);
                    }
                }
        );
    }

    /**
     * 批量删除选中商品（编辑模式下点击“删除”时调用）
     */
    public void deleteSelectedItems() {
        Set<Integer> selectedIds = selectedCartIds.getValue();
        if (selectedIds == null || selectedIds.isEmpty()) {
            showToast("请选择要删除的商品");
            return;
        }

        // 拼接购物车ID（用逗号分隔，如“1,2,3”）
        String cartIds = selectedIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        mModel.deleteCart(cartIds, new IRequestCallback<ResBase<Void>>() {
                    @Override
                    public void onLoadFinish(ResBase<Void> datas) {
                        // 删除成功：重新获取列表，重置选中状态
                        // 立即从当前列表中移除被选中的商品
                        List<ResCartList> currentList = mCartList.getValue();
                        if (currentList != null) {
                            List<ResCartList> newList = new ArrayList<>();
                            for (ResCartList item : currentList) {
                                if (!selectedIds.contains(item.getId())) {
                                    newList.add(item);
                                }
                            }
                            // 立即更新列表
                            mCartList.setValue(newList);
                        }

                        // 重置选中状态
                        selectedCartIds.setValue(new HashSet<>());
                        showToast(datas.getMsg());

                        // 重新获取最新数据
                        getCartList();

                    }

                    @Override
                    public void onLoadFail(int errorCode, String message) {
                        showToast("删除失败：" + message);
                        getCartList();
                    }
                }
        );
    }

    /**
     * 获取选中的商品列表，用于结算
     * 格式: [{goods_id: 12, goods_num: 1, goods_sku_price_id: 20}]
     */
    public List<Map<String, Object>> getSelectedGoodsList() {
        List<ResCartList> cartList = mCartList.getValue();
        Set<Integer> selectedIds = selectedCartIds.getValue();
        List<Map<String, Object>> selectedGoodsList = new ArrayList<>();

        if (cartList == null || selectedIds == null || selectedIds.isEmpty()) {
            return selectedGoodsList;
        }

        for (ResCartList cart : cartList) {
            if (selectedIds.contains(cart.getId())) {
                Map<String, Object> goodsMap = new HashMap<>();
                goodsMap.put("goods_id", cart.getGoods_id());
                goodsMap.put("goods_num", cart.getGoods_num());
                goodsMap.put("goods_sku_price_id", cart.getGoods_sku_price_id());
                selectedGoodsList.add(goodsMap);
            }
        }

        return selectedGoodsList;
    }


    public MutableLiveData<List<ResCartList>> getmCartList() {
        return mCartList;
    }

    public MutableLiveData<Boolean> getIsEditMode() {
        return isEditMode;
    }

    public MutableLiveData<Set<Integer>> getSelectedCartIds() {
        return selectedCartIds;
    }

    public MutableLiveData<String> getTotalPrice() {
        return totalPrice;
    }

    public MutableLiveData<Integer> getSumList() {
        return mSumList;
    }

    public MutableLiveData<Boolean> getIsOperationEnable() {
        return isOperationEnable;
    }

    public MutableLiveData<Boolean> getIsAllSelected() {
        return isAllSelected;
    }
}
