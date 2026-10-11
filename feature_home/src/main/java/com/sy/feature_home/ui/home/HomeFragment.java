package com.sy.feature_home.ui.home;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sy.feature_home.R;
import com.sy.feature_home.adapter.HomeDataAdapter;
import com.sy.feature_home.bean.ResGoodsDetail;
import com.sy.feature_home.databinding.LayoutFragmentHomeBinding;
import com.sy.libbase.base.BaseFragment;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.utils.StatusBarUtils;
import com.sy.network.bean.ResHomeItem;

import java.util.List;

@Route(path = ARouterPath.Home.FRAGMENT_HOME)
public class HomeFragment extends BaseFragment<LayoutFragmentHomeBinding, HomeViewModel> {
    private static final String TAG = "HomeFragment";
    private HomeDataAdapter mAdapter;

    @Override
    protected HomeViewModel getViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_fragment_home;
    }

    @Override
    protected int getBindingVariableId() {
        return 0;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        RecyclerView rv1 = mDataBinding.rv1;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else if (position ==1||position==2) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rv1.setLayoutManager(layoutManager);
        mAdapter = new HomeDataAdapter();
        rv1.setAdapter(mAdapter);

        mAdapter.setmListener(new HomeDataAdapter.ItemHotsaleListener() {
            @Override
            public void onItemClick(int goodsId) {
                ARouter.getInstance().build(ARouterPath.Home.ACTIVITY_GOODS_DETAIL)
                        .withInt(ARouterPath.Home.KEY_GOODS_ID,goodsId).navigation();
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel.requestDatas();
        mViewModel.getDatas().observe(this, new Observer<List<ResHomeItem>>() {
            @Override
            public void onChanged(List<ResHomeItem> resHomeItems) {
                mAdapter.setDatas(resHomeItems);
            }
        });
        mViewModel.getIds().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String ids) {
                Log.i(TAG, "getIds() ids: "+ids);
                mViewModel.requestGoodsDetailByIds(ids);
            }
        });
        mViewModel.getGoodsDetailDatas().observe(this, new Observer<List<ResGoodsDetail>>() {
            @Override
            public void onChanged(List<ResGoodsDetail> resGoodsDetails) {
                mAdapter.setGoodsDatas(resGoodsDetails);
            }
        });
    }
}
