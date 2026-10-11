package com.sy.feature_shoppingcart.ui.pay;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sy.feature_shoppingcart.BR;
import com.sy.feature_shoppingcart.R;
import com.sy.feature_shoppingcart.adapter.ConfirmOrderAdapter;
import com.sy.feature_shoppingcart.bean.ReqOrderCal;
import com.sy.feature_shoppingcart.bean.ResCreatOrder;
import com.sy.feature_shoppingcart.bean.ResDefaultAddress;
import com.sy.feature_shoppingcart.bean.ResOrderCal;
import com.sy.feature_shoppingcart.databinding.ActivityConfirmOrderBinding;
import com.sy.libbase.base.BaseActivity;
import com.sy.libbase.bean.ResAddress;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.eventbus.MessageEvent;
import com.sy.libbase.utils.StatusBarUtils;
import com.sy.network.bean.ResBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

@Route(path = ARouterPath.Shopcart.ACTIVITY_CONFIRM_ORDER)
public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding,ConfirmOrderViewModel> {
    private static final String TAG = "ConfirmOrderActivity";
    @Autowired(name = ARouterPath.Shopcart.KEY_CONFIRM_ORDER)
    String goodsListJson; // 如果使用JSON方式传递
    private ConfirmOrderAdapter mAdapter;

    @Override
    protected ConfirmOrderViewModel getViewModel() {
        return new ViewModelProvider(this).get(ConfirmOrderViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected int getBindingVariableId() {
        return BR.orderviewmodel;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        mAdapter = new ConfirmOrderAdapter();
        mDataBinding.rvGoods.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.rvGoods.setAdapter(mAdapter);

        mViewModel.getAction().observe(this, new Observer<ConfirmOrderViewModel.ConfirmOrderAction>() {
            @Override
            public void onChanged(ConfirmOrderViewModel.ConfirmOrderAction confirmOrderAction) {
                if (confirmOrderAction== ConfirmOrderViewModel.ConfirmOrderAction.NAVIGATION_TO_DELIVERY_ADDRESS){
                    ARouter.getInstance().build(ARouterPath.User.ACTIVITY_ADDRESS).navigation();
                }else {

                }
            }
        });

        mViewModel.getCreatOrderDatas().observe(this, new Observer<ResCreatOrder>() {
            @Override
            public void onChanged(ResCreatOrder resCreatOrder) {
                if (resCreatOrder!=null){
                    //点击提交订单之后，会发起一个网络请求创建订单，拿到订单的order_sn之后，跳转到收银台，将付的金额传递过去，
                    // 同时，这时订单已经创建成功，会从购物车删除订单里的购物车商品，这时候，要再次发起请求购物车列表的请求，以返回购物车时能立即更新ui
                    String payFee = resCreatOrder.getPay_fee();
                    String orderSn = resCreatOrder.getOrder_sn();
                    ARouter.getInstance().build(ARouterPath.User.ACTIVITY_CHECKOUT)
                            .withString(ARouterPath.Shopcart.KEY_PAY_FEE,payFee)
                            .withString(ARouterPath.Shopcart.KEY_ORDER_SN,orderSn).navigation();
                    //可以用evenbus去通知上一个页面，也就是购物车，要重新请求购物车列表
                    //。。。
                    Log.i(TAG, "onChanged: 订单id："+ orderSn);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Log.i(TAG, "initData: 确认订单activity收到的："+goodsListJson);
        List<ReqOrderCal.GoodsListBean> goodsListBeans = convertToGoodsList(goodsListJson);
        //一进入该页面，获取默认地址
        mViewModel.getDefaultAddress();

        mViewModel.getmDefaultAddress().observe(this, new Observer<ResDefaultAddress>() {
            @Override
            public void onChanged(ResDefaultAddress resDefaultAddress) {
                if (resDefaultAddress==null){
                    //控制显示的地址布局是哪个
                    mDataBinding.llNoAddress.setVisibility(View.VISIBLE);
                    mDataBinding.ctlDefaultAddress.setVisibility(View.GONE);
                    mDataBinding.ctlNormalAddress.setVisibility(View.GONE);

                    //一进入该页面，获取默认地址，然后进行订单计算的网络请求
                    ReqOrderCal reqOrderCal = new ReqOrderCal("goods", "cart", goodsListBeans);
                    mViewModel.requestOrderCal(reqOrderCal);
                }else {
                    //有默认地址，记录当前默认地址id
                    mViewModel.getAddressId().setValue(resDefaultAddress.getId());

                    //设置默认地址数据显示
                    mViewModel.getProvinceAndMunicipal().setValue(resDefaultAddress.getProvince_name()+" "+resDefaultAddress.getCity_name()+" "+resDefaultAddress.getDistrict_name());
                    mViewModel.getDetailAddress().setValue(resDefaultAddress.getAddress());
                    mViewModel.getNameAndPhone().setValue(resDefaultAddress.getConsignee()+" "+resDefaultAddress.getMobile());

                    mDataBinding.llNoAddress.setVisibility(View.GONE);
                    mDataBinding.ctlDefaultAddress.setVisibility(View.VISIBLE);
                    mDataBinding.ctlNormalAddress.setVisibility(View.GONE);

                    //一进入该页面，获取默认地址，然后进行订单计算的网络请求
                    ReqOrderCal reqOrderCal = new ReqOrderCal("goods", "cart", resDefaultAddress.getId(),goodsListBeans);
                    mViewModel.requestOrderCal(reqOrderCal);
                }
            }
        });

        mViewModel.getCalDatas().observe(this, new Observer<ResOrderCal>() {
            @Override
            public void onChanged(ResOrderCal resOrderCal) {
                if (resOrderCal!=null){
                    mAdapter.setDatas(resOrderCal.getGoods_list());
                }
            }
        });

        mDataBinding.tvSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.creatOrder(mViewModel.getAddressId().getValue(),goodsListBeans);
            }
        });
    }

    /**
     * 通过eventBus订阅选中收货地址的消息
     *
     * @param event
     * @Subscribe ：订阅一个事件
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN) //表示接收粘性事件 表示这个方法在主线程做(默认)
    public void onMessageEvent(MessageEvent.SelectAddressEvent event) {
        ResAddress selectAddress = event.getSelectAddress();
        Log.i(TAG, "onMessageEvent: SelectAddressEvent " + selectAddress.getMobile());

        if (selectAddress.isIs_default()){
            //控制显示的地址布局是哪个
            mDataBinding.llNoAddress.setVisibility(View.GONE);
            mDataBinding.ctlDefaultAddress.setVisibility(View.VISIBLE);
            mDataBinding.ctlNormalAddress.setVisibility(View.GONE);
        }else {
            mDataBinding.llNoAddress.setVisibility(View.GONE);
            mDataBinding.ctlDefaultAddress.setVisibility(View.GONE);
            mDataBinding.ctlNormalAddress.setVisibility(View.VISIBLE);
        }
        //选中地址之后，记录当前选中的地址id
        mViewModel.getAddressId().setValue(selectAddress.getId());
        //设置选中地址的数据显示
        mViewModel.getProvinceAndMunicipal().setValue(selectAddress.getProvince_name()+" "+selectAddress.getCity_name()+" "+selectAddress.getDistrict_name());
        mViewModel.getDetailAddress().setValue(selectAddress.getAddress());
        mViewModel.getNameAndPhone().setValue(selectAddress.getConsignee()+" "+selectAddress.getMobile());

    }

    @Override
    public void onStart() {
        super.onStart();
        //在onStart中注册是因为 确保页面在活跃的时候再接收事件
        EventBus.getDefault().register(this);//注册eventBus
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            //在onStop中注销是因为 确保页面在不活跃的时候取消事件的接收
            //比如fragment被覆盖就会触发onStop
            EventBus.getDefault().unregister(this);//注销
        }
    }

    /**
     * 将JSON字符串转换为GoodsListBean列表
     */
    public List<ReqOrderCal.GoodsListBean> convertToGoodsList(String jsonString) {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ReqOrderCal.GoodsListBean>>(){}.getType();
            return gson.fromJson(jsonString, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}