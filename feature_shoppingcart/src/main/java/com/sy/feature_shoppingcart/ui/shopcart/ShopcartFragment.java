package com.sy.feature_shoppingcart.ui.shopcart;

import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.sy.feature_shoppingcart.BR;
import com.sy.feature_shoppingcart.R;
import com.sy.feature_shoppingcart.adapter.CartAdapter;
import com.sy.feature_shoppingcart.databinding.LayoutFragmentShopcartBinding;
import com.sy.libbase.base.BaseFragment;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.utils.StatusBarUtils;

import java.util.List;
import java.util.Map;

@Route(path = ARouterPath.Shopcart.FRAGMENT_SHOPCART)
public class ShopcartFragment extends BaseFragment<LayoutFragmentShopcartBinding, ShopcartViewModel> {
    private static final String TAG = "ShopcartFragment";
    private CartAdapter mAdapter;

    @Override
    protected ShopcartViewModel getViewModel() {
        return new ViewModelProvider(this).get(ShopcartViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_fragment_shopcart;
    }

    @Override
    protected int getBindingVariableId() {
        return BR.viewmodel;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        mAdapter = new CartAdapter(getContext(), mViewModel);
        mDataBinding.rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.rvCart.setAdapter(mAdapter);

        // 2. 绑定右上角“编辑/取消”按钮点击事件
        bindEditButton();

        // 3. 绑定全选Checkbox点击事件
        bindAllSelectCheckbox();

        // 4. 绑定“去结算/删除”按钮点击事件
        bindOperationButton();

    }

    @Override
    protected void initData() {
        mViewModel.getCartList();
        // 观察购物车列表变化，更新Adapter数据
        mViewModel.getmCartList().observe(this, cartList -> {
            if (cartList != null) {
                mAdapter.setDatas(cartList);
            }
        });

        // 观察ViewModel数据变化，更新UI
        observeViewModelData();
    }

    /**
     * 绑定右上角“编辑/取消”按钮
     */
    private void bindEditButton() {
        mDataBinding.tvEdit.setOnClickListener(v -> {
            // 通知ViewModel切换编辑模式
            mViewModel.toggleEditMode();
        });
    }

    /**
     * 绑定全选Checkbox
     */
    private void bindAllSelectCheckbox() {
        mDataBinding.cbCheckSum.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 通知ViewModel切换全选状态
            mViewModel.toggleAllSelected(isChecked);
        });
    }

    /**
     * 绑定“去结算/删除”按钮
     */
    private void bindOperationButton() {
        mDataBinding.tvCal.setOnClickListener(v -> {
            boolean isEditMode = mViewModel.getIsEditMode().getValue() != null
                    ? mViewModel.getIsEditMode().getValue()
                    : false;

            if (isEditMode) {
                // 编辑模式：删除选中商品
                mViewModel.deleteSelectedItems();
            } else {
                // 正常模式：跳转结算页
                List<Map<String, Object>> selectedGoodsList = mViewModel.getSelectedGoodsList();
                int selectedCount = selectedGoodsList.size();

                if (selectedCount == 0) {
                    mViewModel.showToast("请选择要结算的商品");
                    return;
                }
                // 打印选中的商品列表（用于调试）
                Log.i("ShopcartFragment", "选中的商品列表: " + selectedGoodsList.toString());
                mViewModel.showToast("跳转结算页，共" + selectedCount + "件商品");

                // 跳转到结算页面，传递选中的商品列表
                // 方法1: 使用JSON字符串传递复杂对象
                String goodsListJson = new Gson().toJson(selectedGoodsList);
                ARouter.getInstance()
                        .build(ARouterPath.Shopcart.ACTIVITY_CONFIRM_ORDER)
                        .withString(ARouterPath.Shopcart.KEY_CONFIRM_ORDER, goodsListJson)
                        .navigation();
                Log.i(TAG, "bindOperationButton: 确认订单的商品列表 "+goodsListJson);
            }
        });
    }

    /**
     * 观察ViewModel数据变化，更新UI
     */
    private void observeViewModelData() {
        // 1. 观察编辑模式：更新“编辑/取消”按钮文字和颜色
        mViewModel.getIsEditMode().observe(this, isEditMode -> {
            if (isEditMode) {
                // 编辑模式：文字“取消”，颜色灰色
                mDataBinding.tvEdit.setText("取消");
                mDataBinding.tvEdit.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

                // 同时更新操作按钮文字为"删除"
                mDataBinding.tvCal.setText("删除");
            } else {
                // 正常模式：文字“编辑”，颜色红色（原颜色#fff6380d）
                mDataBinding.tvEdit.setText("编辑");
                mDataBinding.tvEdit.setTextColor(ContextCompat.getColor(getContext(), R.color.jcinth));

                // 同时更新操作按钮文字为"去结算（X）"
                int selectedCount = mViewModel.getSelectedCartIds().getValue() != null
                        ? mViewModel.getSelectedCartIds().getValue().size()
                        : 0;
                mDataBinding.tvCal.setText(String.format("去结算（%d）", selectedCount));
            }
        });

        // 2. 观察全选状态：同步全选Checkbox
        mViewModel.getIsAllSelected().observe(this, isAllSelected -> {
            // 避免Checkbox点击事件循环触发，先移除监听再设置
            mDataBinding.cbCheckSum.setOnCheckedChangeListener(null);
            mDataBinding.cbCheckSum.setChecked(isAllSelected);
            bindAllSelectCheckbox(); // 重新绑定监听
        });

        // 3. 观察合计金额：更新“合计:¥XX.XX”
        mViewModel.getTotalPrice().observe(this, totalPrice -> {
            mDataBinding.tvTotalPrice.setText("合计:" + totalPrice);
        });

        // 4. 观察操作按钮可用性：控制"去结算/删除"按钮是否可点击
        mViewModel.getIsOperationEnable().observe(this, isEnable -> {
            mDataBinding.tvCal.setEnabled(isEnable);
        });

        // 5. 观察商品总数：更新顶部“共X件商品”
        mViewModel.getSumList().observe(this, sum -> {
            mDataBinding.tvCartsum.setText(String.format("共%d件商品", sum));
        });

        // 5. 观察选中数量变化：在非编辑模式下更新"去结算（X）"的文字
        mViewModel.getSelectedCartIds().observe(this, selectedIds -> {
            boolean isEditMode = mViewModel.getIsEditMode().getValue() != null
                    ? mViewModel.getIsEditMode().getValue()
                    : false;
            if (!isEditMode) {
                // 只在非编辑模式下更新"去结算（X）"的文字
                int selectedCount = selectedIds != null ? selectedIds.size() : 0;
                mDataBinding.tvCal.setText(String.format("去结算（%d）", selectedCount));
            }
        });
    }
}
