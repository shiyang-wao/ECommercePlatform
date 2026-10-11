package com.sy.feature_home.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.sy.feature_home.R;
import com.sy.feature_home.bean.ResGoodsMoreDetail;
import com.sy.feature_home.bean.ResSkuSelect;
import com.sy.feature_home.databinding.LayoutDialogGoodsSelectBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsSelectDialog extends DialogFragment {


    private final Callback mCallback;
    private ResGoodsMoreDetail mGoodsData;

    // 控件绑定（ViewBinding，需确保XML文件名与Binding类名匹配，如layout_dialog_goods_select.xml→LayoutDialogGoodsSelectBinding）
    private LayoutDialogGoodsSelectBinding mBinding;
    private List<ResGoodsMoreDetail.SkusBean> mSkuGroups; // 规格组（如“颜色”）
    private List<ResGoodsMoreDetail.SkuPricesBean> mSkuDetails; // 规格组合→库存/价格映射
    // 选择状态
    private Map<Integer, ResGoodsMoreDetail.SkusBean.SkuOption> mSelectedMap = new HashMap<>(); // key：规格组ID，value：选中的规格项
    private ResGoodsMoreDetail.SkuPricesBean mMatchedSku; // 匹配到的规格组合详情
    private int mSelectCount = 1; // 默认购买数量
    private int mMaxCount = 1; // 最大购买数量（默认1，选中规格后更新为库存）

    public GoodsSelectDialog(ResGoodsMoreDetail datas,Callback callback) {
        mGoodsData=datas;
        mCallback = callback;
        // 初始化规格数据
        if (mGoodsData != null) {
            this.mSkuGroups = mGoodsData.getSkus();
            this.mSkuDetails = mGoodsData.getSku_prices();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 2. 提前获取Window并设置宽度（在show()之前）
        Window window = getDialog().getWindow();
        if (window != null) {
            // 1. 获取屏幕宽度（兼容所有版本）
            DisplayMetrics displayMetrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenWidth = displayMetrics.widthPixels;

            WindowManager.LayoutParams lp = window.getAttributes();
            // 设置宽度为屏幕宽度（与OnShow中效果一致，但提前执行）
            lp.width = screenWidth;
            // 其他属性（如位置、高度等）也可以在这里提前设置
            lp.gravity = Gravity.BOTTOM; // 底部显示
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.getDecorView().setPadding(0, 0, 0, dp2px(10)); // 清除左右padding
            // 应用属性（提前生效，无需等待OnShow）
            window.setAttributes(lp);
        }
    }

    /**
     * 将获取Dialog实例和显示弹窗一起封装起来，外部只需要一个方法就可以显示弹窗
     * @param activity
     * @param datas  在商品详情页传过来的商品详情数据
     * @param callback
     */
    public static void showDialog(FragmentActivity activity, ResGoodsMoreDetail datas, Callback callback){
        GoodsSelectDialog goodsSelectDialog = newInstance(datas, callback);
        goodsSelectDialog.show(activity.getSupportFragmentManager(),"goodsSelect");
    }

    /**
     * 可以快速获得一个GoodsSelectDialog的实例
     * 每个fragment都可以打new快捷生成一个newInstance方法
     * @return
     */
    private static GoodsSelectDialog newInstance(ResGoodsMoreDetail datas,Callback callback) {
        GoodsSelectDialog fragment = new GoodsSelectDialog(datas,callback);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // 初始化ViewBinding
        mBinding = LayoutDialogGoodsSelectBinding.inflate(getLayoutInflater());
        // 绑定商品数据到XML
        mBinding.setData(mGoodsData);
        mBinding.tvPrice.setText("￥"+mGoodsData.getPrice().get(0));
        mBinding.executePendingBindings(); // 立即执行数据绑定，避免UI延迟

        // 1. 初始化弹窗样式（全屏、无标题、背景透明）
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), com.sy.libbase.R.style.FullScreenDialogStyle);
        //布局关联dialog
        builder.setView(mBinding.getRoot());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true); // 点击外部取消
        setCancelable(true);

        // 2. 动态生成规格选择区
        generateSkuViews();

        // 3. 初始化数量增减逻辑
        initCountControl();

        // 4. 初始化按钮点击事件
        initButtonClick();

         //5. 设置弹窗宽度（占屏幕90%）
//        dialog.setOnShowListener(dialogInterface -> {
////            Window window = dialog.getWindow();
//            if (window != null) {
//                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.width = getResources().getDisplayMetrics().widthPixels;
//                window.setAttributes(lp);
//                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 去除默认背景
//            }
//        });

        // 6. 弹窗取消监听
        dialog.setOnCancelListener(dialogInterface -> {
            if (mCallback != null) {
                mCallback.onCancel();
            }
        });

        return dialog;
    }

    /**
     * 动态生成规格选择视图（每个规格组+自动换行的规格项）
     */
    private void generateSkuViews() {
        if (mSkuGroups == null || mSkuGroups.isEmpty()) {
            return;
        }

        // 遍历每个规格组（如“颜色”）
        for (ResGoodsMoreDetail.SkusBean skuGroup : mSkuGroups) {
            int groupId = skuGroup.getId();
            String groupName = skuGroup.getName();
            List<ResGoodsMoreDetail.SkusBean.SkuOption> skuOptions = skuGroup.getChildren();

            // ① 规格组标题（如“颜色：”）
            TextView tvGroupTitle = new TextView(getContext());
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            titleParams.bottomMargin = dp2px(8);
            titleParams.topMargin = dp2px(8); // 各组之间加一点间距
            tvGroupTitle.setLayoutParams(titleParams);
            tvGroupTitle.setTextSize(14);
            tvGroupTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.ff333333));
            tvGroupTitle.setText(groupName + "：");
            mBinding.llSpecContainer.addView(tvGroupTitle);

            // ② 规格项容器（ConstraintLayout + Flow 实现自动换行）
            ConstraintLayout skuContainer = new ConstraintLayout(getContext());
            LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            skuContainer.setLayoutParams(containerParams);
            mBinding.llSpecContainer.addView(skuContainer);

            // ③ 创建 Flow 辅助布局（管理规格项的排列）
            Flow flow = new Flow(getContext());
            flow.setId(View.generateViewId()); // 给 Flow 设置唯一ID
            flow.setOrientation(Flow.HORIZONTAL); // 水平排列
            flow.setHorizontalGap(dp2px(12)); // 水平间距
            flow.setVerticalGap(dp2px(8)); // 垂直间距（换行后的行间距）
            flow.setWrapMode(Flow.WRAP_CHAIN); // 超出自动换行
            // 将 Flow 约束到容器的四边（占满容器宽度）
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(skuContainer);
            constraintSet.connect(flow.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(flow.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            constraintSet.connect(flow.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraintSet.connect(flow.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            skuContainer.addView(flow);

            // ④ 动态添加规格项（如“白色”“黑色”），并交给 Flow 管理
            List<Integer> skuViewIds = new ArrayList<>(); // 存储规格项的ID，用于Flow管理
            if (skuOptions != null && !skuOptions.isEmpty()) {
                for (ResGoodsMoreDetail.SkusBean.SkuOption option : skuOptions) {
                    TextView tvOption = new TextView(getContext());
                    int viewId = View.generateViewId(); // 每个规格项设置唯一ID
                    tvOption.setId(viewId);
                    skuViewIds.add(viewId);

                    // 设置规格项大小和边距
                    ConstraintLayout.LayoutParams optionParams = new ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            dp2px(28) // 固定高度
                    );
                    tvOption.setLayoutParams(optionParams);
                    tvOption.setGravity(Gravity.CENTER);

                    // 未选中样式
                    tvOption.setBackgroundResource(R.drawable.bg_spec_unselected);
                    tvOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.ff333333));
                    tvOption.setTextSize(13); // 文字大小13sp，确保清晰
                    tvOption.setPadding(dp2px(12), 0, dp2px(12), 0); // 左右内边距
                    tvOption.setText(option.getName());

                    // 规格项点击事件（单选）
                    tvOption.setOnClickListener(v -> {
                        // 记录当前组的选中项
                        mSelectedMap.put(groupId, option);
                        // 更新该组所有规格项的样式
                        updateSkuOptionStyle(skuContainer, option.getName());
                        // 匹配规格组合，更新库存和价格
                        matchSkuDetail();
                        // 更新最大购买数量（为当前规格的库存）
                        mMaxCount = mMatchedSku != null ? mMatchedSku.getStock() : 1;
                        // 确保当前数量不超过最大库存
                        if (mSelectCount > mMaxCount) {
                            mSelectCount = mMaxCount;
                            mBinding.tvSelectSum.setText(String.valueOf(mSelectCount));
                        }
                    });

                    skuContainer.addView(tvOption);
                }

                // ⑤ 关键：直接通过 Flow 的 setReferencedIds 关联规格项ID，实现自动换行
                if (!skuViewIds.isEmpty()) {
                    int[] ids = skuViewIds.stream().mapToInt(Integer::intValue).toArray();
                    flow.setReferencedIds(ids); // 直接告诉 Flow 需要管理哪些子视图
                }
            }
        }
    }

    /**
     * 更新规格项选中样式（同一组只能选一个）
     */
    private void updateSkuOptionStyle(ConstraintLayout container, String selectedName) {
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = container.getChildAt(i);
            // 过滤掉Flow本身，只处理TextView类型的规格项
            if (child instanceof TextView) {
                TextView tvOption = (TextView) child;
                if (selectedName.equals(tvOption.getText().toString())) {
                    // 选中样式
                    tvOption.setBackgroundResource(R.drawable.bg_spec_selected);
                    tvOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
                } else {
                    // 未选中样式
                    tvOption.setBackgroundResource(R.drawable.bg_spec_unselected);
                    tvOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.ff333333));
                }
            }
        }
    }

    /**
     * 匹配规格组合：根据已选规格，找到对应的SkuPricesBean（库存/价格）
     */
    private void matchSkuDetail() {
        // 1. 检查是否选完所有规格组
        if (mSkuGroups == null || mSelectedMap.size() != mSkuGroups.size()) {
            mMatchedSku = null;
            mBinding.tvStock.setText("库存：请选择完整规格");
            mBinding.tvPrice.setText("￥" + mGoodsData.getPrice().get(0)); // 显示默认价格
            return;
        }

        // 2. 提取已选规格名称列表（顺序与规格组一致，确保匹配sku_prices的goods_sku_text）
        List<String> selectedNames = new ArrayList<>();
        for (ResGoodsMoreDetail.SkusBean group : mSkuGroups) {
            ResGoodsMoreDetail.SkusBean.SkuOption option = mSelectedMap.get(group.getId());
            if (option != null) {
                selectedNames.add(option.getName());
            }
        }

        // 3. 遍历sku_prices，匹配规格组合
        if (mSkuDetails != null) {
            for (ResGoodsMoreDetail.SkuPricesBean sku : mSkuDetails) {
                List<String> skuNames = sku.getGoods_sku_text();
                if (skuNames != null && skuNames.equals(selectedNames)) {
                    // 匹配成功
                    mMatchedSku = sku;
                    // 更新价格（优先显示活动价，无活动则显示普通价）
                    String finalPrice = getActivityPrice(sku);
                    mBinding.tvPrice.setText("￥" + finalPrice);
                    // 更新库存
                    int stock = sku.getStock();
                    mBinding.tvStock.setText(String.format("库存：%d件", stock));
                    mBinding.tvStock.setTextColor(stock <= 0 ?
                            getResources().getColor(R.color.F6380D) :
                            getResources().getColor(R.color.ff8d8d8d));
                    return;
                }
            }
        }

        // 4. 未匹配到（异常情况）
        mMatchedSku = null;
        mBinding.tvStock.setText("库存：该规格组合不存在");
    }

    /**
     * 获取活动价（如有秒杀活动，优先显示活动价；无则显示普通价）
     */
    private String getActivityPrice(ResGoodsMoreDetail.SkuPricesBean sku) {
        if (mGoodsData.getActivities() == null || mGoodsData.getActivities().isEmpty()) {
            return sku.getPrice(); // 无活动，返回普通价
        }

        // 遍历活动，找到当前规格的活动价（以秒杀活动为例）
        for (ResGoodsMoreDetail.ActivitiesBean activity : mGoodsData.getActivities()) {
            if ("seckill".equals(activity.getType()) && "ing".equals(activity.getStatus())) {
                List<ResGoodsMoreDetail.ActivitiesBean.ActivitySkuPricesBean> activitySkus = activity.getActivity_sku_prices();
                if (activitySkus != null) {
                    for (ResGoodsMoreDetail.ActivitiesBean.ActivitySkuPricesBean activitySku : activitySkus) {
                        if (activitySku.getGoods_sku_price_id() == sku.getId() && "up".equals(activitySku.getStatus())) {
                            return activitySku.getPrice(); // 返回活动价
                        }
                    }
                }
            }
        }
        return sku.getPrice(); // 无活动价，返回普通价
    }

    /**
     * 初始化数量增减控制
     */
    private void initCountControl() {
        // 减号点击（最小1）
        mBinding.ivSubtract.setOnClickListener(v -> {
            if (mSelectCount > 1) {
                mSelectCount--;
                mBinding.tvSelectSum.setText(String.valueOf(mSelectCount));
            }
        });

        // 加号点击（最大不超过库存）
        mBinding.ivAdd.setOnClickListener(v -> {
            if (mMatchedSku == null) {
                Toast.makeText(getContext(), "请先选择规格", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mSelectCount < mMaxCount) {
                mSelectCount++;
                mBinding.tvSelectSum.setText(String.valueOf(mSelectCount));
            } else {
                Toast.makeText(getContext(), "已达到最大购买数量", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化按钮点击事件（加入购物车/立即购买）
     */
    private void initButtonClick() {
        // 加入购物车
        mBinding.btnAddCart.setOnClickListener(v -> {
            if (checkSelectValid()) {
                ResSkuSelect result = createSelectResult();
                mCallback.onConfirm(result, Callback.TYPE_ADD_CART);
                dismiss();
            }
        });

        // 立即购买
        mBinding.btnBuyNow.setOnClickListener(v -> {
            if (checkSelectValid()) {
                ResSkuSelect result = createSelectResult();
                mCallback.onConfirm(result, Callback.TYPE_BUY_NOW);
                dismiss();
            }
        });
    }

    /**
     * 检查选择是否有效（规格是否选完+库存是否充足）
     */
    private boolean checkSelectValid() {
        if (mMatchedSku == null) {
            Toast.makeText(getContext(), "请选择完整规格", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mMatchedSku.getStock() <= 0) {
            Toast.makeText(getContext(), "该规格已售罄", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mSelectCount > mMatchedSku.getStock()) {
            Toast.makeText(getContext(), "购买数量超过库存", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 创建选择结果（ResSkuSelect）
     */
    private ResSkuSelect createSelectResult() {
        // 1. 拼接选中的规格组合（如“白色”）
        StringBuilder specBuilder = new StringBuilder();
        List<ResGoodsMoreDetail.SkusBean.SkuOption> selectedOptions = new ArrayList<>();
        for (ResGoodsMoreDetail.SkusBean group : mSkuGroups) {
            ResGoodsMoreDetail.SkusBean.SkuOption option = mSelectedMap.get(group.getId());
            if (option != null) {
                specBuilder.append(option.getName()).append("/");
                selectedOptions.add(option);
            }
        }
        String selectedSpec = specBuilder.length() > 0 ?
                specBuilder.substring(0, specBuilder.length() - 1) : "";

        // 2. 获取最终价格（活动价/普通价）
        String finalPrice = getActivityPrice(mMatchedSku);

        // 3. 构建返回对象
        ResSkuSelect selectResult = new ResSkuSelect(
                mGoodsData.getId(), // 商品ID
                mGoodsData.getTitle(), // 商品标题
                mGoodsData.getImage(), // 商品图片
                selectedSpec, // 选中规格
                finalPrice, // 选中价格
                mSelectCount, // 购买数量
                mMatchedSku.getId()
        );
        // 补充其他字段
        selectResult.setSelectedStock(mMatchedSku.getStock()); // 选中规格的库存
        selectResult.setSelectedOptions(selectedOptions); // 选中的规格项列表

        return selectResult;
    }

    /**
     * dp转px（避免不同屏幕尺寸适配问题）
     */
    private int dp2px(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 弹窗销毁时释放资源
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null; // 释放ViewBinding，避免内存泄漏
    }

    public interface Callback{
        // 确认选择（加入购物车/立即购买共用，用type区分）
        void onConfirm(ResSkuSelect selectResult, int type);
        // 取消弹窗
        void onCancel();
        // 类型常量：1=加入购物车，2=立即购买
        int TYPE_ADD_CART = 1;
        int TYPE_BUY_NOW = 2;
    }
}
