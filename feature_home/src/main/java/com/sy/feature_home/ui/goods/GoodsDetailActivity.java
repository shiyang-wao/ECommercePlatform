package com.sy.feature_home.ui.goods;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.appbar.AppBarLayout;
import com.sy.feature_home.BR;
import com.sy.feature_home.R;
import com.sy.feature_home.bean.ResGoodsMoreDetail;
import com.sy.feature_home.bean.ResSkuSelect;
import com.sy.feature_home.databinding.ActivityGoodsDetailBinding;
import com.sy.feature_home.ui.dialog.GoodsSelectDialog;
import com.sy.feature_home.ui.goods.image.ImageDetailFragment;
import com.sy.feature_home.ui.goods.image.ImageFragment;
import com.sy.libbase.api.CartService;
import com.sy.libbase.api.LoginService;
import com.sy.libbase.base.BaseActivity;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.dialog.LoginDialog;
import com.sy.libbase.eventbus.MessageEvent;
import com.sy.libbase.manager.UserManager;
import com.sy.libbase.utils.StatusBarUtils;
import com.sy.network.bean.ResBase;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterPath.Home.ACTIVITY_GOODS_DETAIL)
public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding, GoodsDetailViewModel> {
    private static final String TAG = "GoodsDetailActivity";
    private AppBarLayout appBarLayout;
    private NestedScrollView nestedScrollView;
    private TextView tvProduct, tvComment, tvDetail;
    private ViewPager2 ctlProduct;
    private ConstraintLayout ctlCommentT, ctlCommentF, ctlDetail;
    // 定义颜色和样式
//    private final int COLOR_NORMAL = Color.BLACK;
//    private final int COLOR_SELECTED = Color.parseColor("#FF6200EE"); // 选中颜色
    private final Typeface normalTypeface = Typeface.DEFAULT;
    private final Typeface boldTypeface = Typeface.DEFAULT_BOLD;
    private int currentSelected = -1; // 当前选中的索引
    private int lastScrollY = 0;
    private boolean isStatusBarVisible = false;
    private Drawable mSelectFlag;
    private int mAppBarFinalHeight = 70;//状态栏的高度，在动画结束之后获取
    @Autowired(name = ARouterPath.Home.KEY_GOODS_ID)
    public int mId; //接受ARoute传来的key为KEY_GOODS_ID的值
    private ResGoodsMoreDetail mDatas;


    @Override
    protected GoodsDetailViewModel getViewModel() {
        return new ViewModelProvider(this).get(GoodsDetailViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected int getBindingVariableId() {
        return BR.detail;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        // 1. 初始化Drawable并设置宽高（关键修复）
        mSelectFlag = ContextCompat.getDrawable(this, R.drawable.shap_detail_flag_horizon);
        if (mSelectFlag != null) {
            // 转换dp为px（避免不同屏幕密度适配问题）
            int width = dp2px(this, 25); // 指示器宽度（根据UI设计调整）
            int height = dp2px(this, 2);  // 指示器高度（根据UI设计调整）
            mSelectFlag.setBounds(0, 0, width, height); // 设置Drawable的显示区域
        }

        appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.setAlpha(0f);
        nestedScrollView = findViewById(R.id.nested_scroll_view);

        //ViewTreeObserver是一个观察者对象，用于监听视图树（View Tree）的各种状态变化,每个 View 都有一个对应的 ViewTreeObserver,可以监听多种视图事件：布局完成、绘制完成、滚动变化、触摸事件等
        //此处获取了 nestedScrollView 的视图树观察器，通过它可以给这个视图添加各种监听器。
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        // 和直接加监听 nestedScrollView.setOnScrollChangeListener(参数)有点区别，它会自动提供丰富的参数，但此处只有滚动位置参数，需要手动获取其他信息
                        int scrollY = nestedScrollView.getScrollY();
                        handleScrollBehavior(scrollY);
                        lastScrollY = scrollY;
                    }
                });

        mViewModel.getDatas().observe(this, new Observer<ResGoodsMoreDetail>() {
            @Override
            public void onChanged(ResGoodsMoreDetail datas) {
                if (datas != null) {
                    mDatas = datas;
                    mDataBinding.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //为原价设置删除线
                    if (datas.getIs_sku() == 0) {
                        mDataBinding.ctlSkus.setVisibility(View.GONE);
                    } else {
                        mDataBinding.ctlSkus.setVisibility(View.VISIBLE);
                    }
                    if (datas.getParams().isEmpty()) {
                        mDataBinding.ctlParams.setVisibility(View.GONE);
                    } else {
                        mDataBinding.ctlParams.setVisibility(View.VISIBLE);
                    }

                    initViewPager(datas);//初始化商品图片

                }
                if (datas.getContent() != null && !datas.getContent().isEmpty()) {
                    //在加载HTML内容前，注入自适应CSS样式。这样可以解决直接显示图片与屏幕宽度不适配，需要左右滑动的问题
                    String style = "<style>img{max-width:100% !important; height:auto !important;}</style>";
                    String content = "<html><head>" + style + "</head><body>" + datas.getContent() + "</body></html>";
                    mDataBinding.webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
                }
            }
        });

        // 状态栏文本
        tvProduct = findViewById(R.id.tv_product);
        tvComment = findViewById(R.id.tv_comment);
        tvDetail = findViewById(R.id.tv_detail);
        // 目标View
        ctlProduct = findViewById(R.id.view_pager2);
        ctlCommentF = findViewById(R.id.ctl_comment_default); //没有评论时的部分
        ctlCommentT = findViewById(R.id.ctl_comment); //有评论时的部分
        ctlDetail = findViewById(R.id.ctl_detailpic);
        //处理状态栏和滚动之间的响应
        setupClickListeners();
        setupScrollListener();

        mDataBinding.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDatas != null) {
                    showSpecDialog(mDatas);
                }
            }
        });

        mDataBinding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDatas != null) {
                    showSpecDialog(mDatas);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Log.i(TAG, "initData: 商品id：" + mId);
        mViewModel.requestGoodsDetailData(mId);


    }


    /**
     * 显示规格选择弹窗
     *
     * @param goodsData
     */
    private void showSpecDialog(ResGoodsMoreDetail goodsData) {
        GoodsSelectDialog.showDialog(this, goodsData, new GoodsSelectDialog.Callback() {
            @Override
            public void onConfirm(ResSkuSelect selectResult, int type) {
                // 根据类型处理逻辑
                if (type == TYPE_ADD_CART) {
                    Log.i(TAG, "onConfirm: 弹窗回调：" + selectResult);
                    // 加入购物车：调用加入购物车接口
                    addToCart(selectResult);
                } else if (type == TYPE_BUY_NOW) {
                    // 立即购买：跳转到结算页
                    goToCheckout(selectResult);
                }
            }

            @Override
            public void onCancel() {
                // 弹窗取消，无需处理
            }
        });
    }

    /**
     * 加入购物车逻辑
     *
     * @param selectResult
     */
    private void addToCart(ResSkuSelect selectResult) {
        // 调用接口，传入selectResult中的商品ID、规格、数量等参数
        UserManager instance = UserManager.getInstance();
        if (instance.isLogin()) {
            //购物车逻辑
            Log.i(TAG, "addToCart: 开始加入购物车");
            CartService cartService = ARouter.getInstance().navigation(CartService.class);
            if (cartService == null) {
                // 服务获取失败，提示错误
                Log.i(TAG, "onLogin: loginService为空");
                return; // 直接返回，避免后续空指针
            }
            cartService.update(selectResult.getGoodsId(), selectResult.getSelectSum(),
                    selectResult.getGoods_sku_price_id(), "inc", new CartService.CartCallback() {
                        @Override
                        public void onSuccess(ResBase data) {
                            if (data.getCode() == 1) {
                                mViewModel.showToast(data.getMsg());
                            }
                            Log.i(TAG, "onSuccess: 购物车回调结果：" + data.getMsg());
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            mViewModel.showToast(errorMsg);
                        }
                    });
        } else {
            //登录逻辑
            LoginDialog.showDialog(this, new LoginDialog.Callback() {
                @Override
                public void onLogin(String account, String password) {
                    //发起一个登录请求
                    Log.i(TAG, "点击购物车发起登录请求 onLogin: account = " + account + "password = " + password);
                    // 1. 通过ARouter获取登录服务实例（关键：只依赖base库的接口，不依赖user模块）
                    /**
                     * 此处通过ARouter获取到的是LoginService的实现类UserLoginServiceImpl 具体可看 LoginService 的注释
                     */
                    LoginService loginService = ARouter.getInstance().navigation(LoginService.class);
                    if (loginService == null) {
                        // 服务获取失败，提示错误
                        Log.i(TAG, "onLogin: loginService为空");
                        return; // 直接返回，避免后续空指针
                    }
                    if (loginService != null) {
                        // 异常处理：服务未找到（如user模块未集成）
                        loginService.login(account, password, new LoginService.LoginCallback() {
                            @Override
                            public void onSuccess(ResBase<Object> datas, String token) {

                                mViewModel.showToast("登陆成功:" + datas.getMsg());
                                closeLoginDialog();
                                Log.i(TAG, "onSuccess: 最终商品详情页token:" + token);
                            }

                            @Override
                            public void onFailure(String errorMsg) {
                                mViewModel.showToast("登陆失败:" + errorMsg);
                                Log.i(TAG, "onFailure: err" + errorMsg);
                            }
                        });
                    }
                }

                @Override
                public void onCancel() {

                }
            });
        }

    }

    /**
     * 跳转到结算页
     *
     * @param selectResult
     */
    private void goToCheckout(ResSkuSelect selectResult) {
//        Intent intent = new Intent(this, CheckoutActivity.class);
//        intent.putExtra("SELECT_RESULT", selectResult); // 传递选择结果
//        startActivity(intent);
    }

    /**
     * 控制滚动时状态栏的出现与消失
     *
     * @param scrollY
     */
    private void handleScrollBehavior(int scrollY) {
        if (scrollY > 100) { // 滚动超过200px显示状态栏
            if (!isStatusBarVisible) {
                showStatusBar();
                findViewById(R.id.cv_back2).setVisibility(View.GONE);
            }
        } else if (scrollY <= 50) { // 接近顶部时隐藏
            if (isStatusBarVisible) {
                hideStatusBar();
                findViewById(R.id.cv_back2).setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 显示状态栏
     */
    private void showStatusBar() {
        isStatusBarVisible = true;
        appBarLayout.setVisibility(View.VISIBLE);
        appBarLayout.animate()
                .alpha(1f)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        // 动画结束后，获取AppBar的最终高度（此时值才准确）
                        mAppBarFinalHeight = appBarLayout.getHeight();
                        Log.d(TAG, "AppBar最终高度：" + mAppBarFinalHeight);
                    }
                }).start();
    }

    /**
     * 隐藏状态栏
     */
    private void hideStatusBar() {
        isStatusBarVisible = false;
        //.withEndAction() 设置一个动画结束后的回调,当透明度动画完成后自动执行
        appBarLayout.animate().alpha(0f).setDuration(500).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        appBarLayout.setVisibility(View.GONE);
                    }
                }).start();
    }

    /**
     * 初始化商品图片  还有图片详情
     */
    private void initViewPager(ResGoodsMoreDetail datas) {

        //直接关联数据到pager列表  在这里设置了两个viewpager，一个图片滑动，一个点击图片跳出的图片详情滑动，图片数据是一样的
        List<String> images = datas.getImages();
        String baseUrl = "https://shop.fzqq.fun";

        //处理图片点击之后的事件，让详情呢个viewpager出现
        ImageFragment.imageClickListener imageClickListener = new ImageFragment.imageClickListener() {
            @Override
            public void onImageClick(int position) {
                Log.i(TAG, "onImageClick: onImageClick" + position);

                mDataBinding.vpImagedetail.setVisibility(View.VISIBLE);
                // 添加延迟确保 View 已经完成布局
                mDataBinding.vpImagedetail.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.vpImagedetail.setCurrentItem(position);
                    }
                }, 50);
            }
        };
        //处理详情fragment点击之后的事件，让详情的viewpager消失
        ImageDetailFragment.detailImageClickListener imageClickDetailListener = new ImageDetailFragment.detailImageClickListener() {
            @Override
            public void ondetailImageClick() {
                mDataBinding.vpImagedetail.setVisibility(View.GONE);
            }
        };


        //处理详情的呢个viewpager，为它添加fragment
        List<Fragment> detailFragments = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            String url = baseUrl + images.get(i);
            ImageDetailFragment fragment = (ImageDetailFragment) ARouter.getInstance().build(ARouterPath.Home.FRAGMENT_IMAGE_DETAIL)
                    .withString(ARouterPath.Home.KEY_IMAGE_URL, url).navigation();
            fragment.setmListener(imageClickDetailListener);
            detailFragments.add(fragment);
        }
        mDataBinding.vpImagedetail.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return detailFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return detailFragments == null ? 0 : detailFragments.size();
            }
        });

        //处理图片的viewpager，添加fragment，并传两个值，一个是fragment里面的图片url，一个是当前fragment处于viewpager的第几个位置，方便处理点击之后显示的viewpager里面是当前的fragment的图片数据
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            String url = baseUrl + images.get(i);
            ImageFragment fragment = (ImageFragment) ARouter.getInstance().build(ARouterPath.Home.FRAGMENT_IMAGE)
                    .withString(ARouterPath.Home.KEY_IMAGE_URL, url)
                    .withInt(ARouterPath.Home.KEY_IMAGE_POSITION, i).navigation();
            fragment.setmListener(imageClickListener);
            fragments.add(fragment);
        }
        mDataBinding.viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments == null ? 0 : fragments.size();
            }
        });
        //监听图片viewPager2的滑动，更新指示器数据
        mDataBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                String format = String.format("%s/%s", position + 1, fragments.size());
                mDataBinding.tvIndicator.setText(format);

                //ViewPager2 本身可能不响应点击：ViewPager2 主要用于滑动切换，它的点击事件可能被子视图拦截。
                //不论想是在这设置还是直接设置点击事件，都不响应，建议传值到fragment里面设置点击
            }
        });
    }

    /**
     * 为状态栏点击设置监听
     */
    private void setupClickListeners() {
        // 文本1点击事件
        tvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToSection(0, ctlProduct);

            }
        });

        // 文本2点击事件
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View targetCommentView = ctlCommentT.getVisibility() == View.VISIBLE ? ctlCommentT : ctlCommentF;
                scrollToSection(1, targetCommentView);
            }
        });

        // 文本3点击事件
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollToSection(2, ctlDetail);
            }
        });
    }

    /**
     * 滚动到指定章节并更新选中状态
     */
    private void scrollToSection(int sectionIndex, View targetView) {
        // 先滚动（确保位置到位）
        scrollToView(targetView);

        // 确保状态栏可见
        if (appBarLayout.getVisibility() != View.VISIBLE) {
            appBarLayout.setVisibility(View.VISIBLE);
            appBarLayout.setAlpha(1f);
        }

        // 滚动后强制更新状态（避免误判导致状态不匹配）
        nestedScrollView.postOnAnimationDelayed(() -> {
            updateSelectedState(sectionIndex);
            currentSelected = sectionIndex; // 锁定状态，避免滚动中被监听误改
        }, 300); // 等待滚动动画结束（与smoothScrollTo默认时长匹配）

        // 更新选中状态
        updateSelectedState(sectionIndex);


    }

    /**
     * 更新选中状态
     */
    private void updateSelectedState(int selectedIndex) {
        // 重置所有文本样式
        resetAllTextStyles();

        // 设置选中文本的样式
        switch (selectedIndex) {
            case 0:
                setTextSelected(tvProduct, true);
                break;
            case 1:
                setTextSelected(tvComment, true);
                break;
            case 2:
                setTextSelected(tvDetail, true);
                break;
        }

        currentSelected = selectedIndex;
    }

    /**
     * 重置所有文本为未选中状态
     */
    private void resetAllTextStyles() {
        setTextSelected(tvProduct, false);
        setTextSelected(tvComment, false);
        setTextSelected(tvDetail, false);
    }

    /**
     * 设置文本选中状态
     */
    private void setTextSelected(TextView textView, boolean selected) {
        if (selected) {
//            textView.setTextColor(COLOR_SELECTED);
            textView.setTypeface(boldTypeface);
            textView.setCompoundDrawables(null, null, null, mSelectFlag);
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // 选中时稍大
        } else {
//            textView.setTextColor(COLOR_NORMAL);
            textView.setTypeface(normalTypeface);
            textView.setCompoundDrawables(null, null, null, null);
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); // 正常大小
        }
    }

    /**
     * 滚动到指定View
     */
    private void scrollToView(View targetView) {

        // 获取当前滚动位置和目标位置
        int currentScrollY = nestedScrollView.getScrollY();
        int targetY = targetView.getTop();

        // 如果已经在目标位置附近，不重复滚动（阈值可调整）
        int threshold = 30;
        if (Math.abs(currentScrollY - targetY) < threshold) {
            return;
        }

        // 计算需要滚动的距离（考虑状态栏高度）
        int scrollToY = targetY - mAppBarFinalHeight;
        scrollToY = Math.max(scrollToY, 0); // 避免滚动到负数

        // 执行滚动动画
        nestedScrollView.smoothScrollTo(0, scrollToY);
    }

    /**
     * 设置滚动监听，根据当前位置自动更新选中状态
     */
    private void setupScrollListener() {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        autoUpdateSelectedState();
                    }
                });
    }

    /**
     * 根据滚动位置自动更新选中状态
     */
    private void autoUpdateSelectedState() {

        int scrollY = nestedScrollView.getScrollY();
        int windowHeight = getWindowHeight();
        int appBarOffset = appBarLayout.getVisibility() == View.VISIBLE ? mAppBarFinalHeight : 0; // 用动画后获取的真实高度

        // 计算可视区域的上下边界（考虑AppBarLayout）
        int visibleAreaTop = scrollY + appBarOffset;
        int visibleAreaBottom = scrollY + windowHeight;

        // 中间区域：从可见区顶部到30%的位置（范围小，不易误判）  因为默认的评价区域小于屏幕1/3，当点击评价后滚动到相应区域，这时评价和详情顶部都在上1/3，所以这里缩减判断范围，更精准
        /**
         * 状态栏点击滚动之后的位置要和滚动监听判断是状态栏那个文本的位置要大概一致
         * 点击状态栏之后滚动的位置在上面偏顶部，如果在这里滚动监听状态时，使用的判断区域不与点击滚动的位置接近，会导致点击状态栏滚动，又因滚动监听时因判断位置不一致而误判成别的状态栏文本
         * 所以这里判断区域也要在上面偏顶部
         */
        int visibleAreaHeight = visibleAreaBottom - visibleAreaTop;
        int centerZoneTop = visibleAreaTop + (int) (visibleAreaHeight * 0.1f);
        int centerZoneBottom = visibleAreaTop + (int) (visibleAreaHeight * 0.3f);

        // 获取各个section标题的绝对位置（在NestedScrollView内部的位置）
        // 获取各控件的真实位置（处理评价区的两个View）
        View commentView = ctlCommentT.getVisibility() == View.VISIBLE ? ctlCommentT : ctlCommentF;
        int productTop = ctlProduct.getTop();
        int commentTop = commentView.getTop();
        int detailTop = ctlDetail.getTop();
        // 新增：获取各控件的底部位置（判断主体是否可见）
        int productBottom = ctlProduct.getBottom();
        int commentBottom = commentView.getBottom();
        int detailBottom = ctlDetail.getBottom();

        //判断“控件主体是否在可见区”且“控件主体是否在中间区域”
        boolean isProductVisible = (productBottom > visibleAreaTop) && (productTop < visibleAreaBottom);
        boolean isCommentVisible = (commentBottom > visibleAreaTop) && (commentTop < visibleAreaBottom);
        boolean isDetailVisible = (detailBottom > visibleAreaTop) && (detailTop < visibleAreaBottom);

        // 4. 优先根据“主体可见”+“中间区域”判定状态（避免误判）
        if (isDetailVisible && (detailTop < centerZoneBottom && detailBottom > centerZoneTop)) {
            updateStateIfNeed(2); // 详情区
        } else if (isCommentVisible && (commentTop < centerZoneBottom && commentBottom > centerZoneTop)) {
            updateStateIfNeed(1); // 评价区
        } else if (isProductVisible) {
            updateStateIfNeed(0); // 商品区（默认兜底）
        }
    }

    /**
     * 辅助方法：仅当状态变化时才更新（避免重复执行）
     *
     * @param targetIndex
     */
    private void updateStateIfNeed(int targetIndex) {
        if (currentSelected != targetIndex) {
            updateSelectedState(targetIndex);
            Log.d(TAG, "更新状态为：" + targetIndex);
        }
    }

    /**
     * 获取窗口高度
     */
    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    // 辅助方法：dp转px（解决屏幕适配）
    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f); // +0.5f是为了四舍五入取整
    }

    // 核心：通过FragmentManager找到弹窗并关闭
    private void closeLoginDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                LoginDialog loginDialog = (LoginDialog) fragmentManager.findFragmentByTag("LoginDialog");

                if (loginDialog != null) {
                    Dialog dialog = loginDialog.getDialog();
                    if (dialog != null && dialog.isShowing()) {
                        Log.i(TAG, "直接关闭Dialog");
                        dialog.dismiss(); // 关键：直接关闭Dialog而不是Fragment
                    } else {
                        // 如果dialog为null或没显示，再尝试关闭fragment
                        loginDialog.dismissAllowingStateLoss();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Activity被销毁: " + this.hashCode());
    }

}