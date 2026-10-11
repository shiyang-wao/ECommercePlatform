package com.sy.feature_categorize.ui.categorize;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sy.feature_categorize.BR;
import com.sy.feature_categorize.adapter.CategoryFirstAdapter;
import com.sy.feature_categorize.R;
import com.sy.feature_categorize.adapter.CategorySecondAdapter;
import com.sy.feature_categorize.bean.ResCategory;
import com.sy.feature_categorize.databinding.LayoutFragmentCategoryBinding;
import com.sy.libbase.base.BaseFragment;
import com.sy.libbase.config.ARouterPath;
import com.sy.libbase.utils.StatusBarUtils;

import java.util.List;

@Route(path = ARouterPath.Category.FRAGMENT_CATEGORY)
public class CategoryFragment extends BaseFragment<LayoutFragmentCategoryBinding,CategoryViewModel> {
    private static final String TAG = "CategoryFragment";
    private CategoryFirstAdapter mAdapterFirst;
    private CategorySecondAdapter mAdapterSecond;

    @Override
    protected CategoryViewModel getViewModel() {
        return new ViewModelProvider(this).get(CategoryViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_fragment_category;
    }

    @Override
    protected int getBindingVariableId() {
        return BR.viewmodel;
    }

    @Override
    protected void initView() {
        StatusBarUtils.addStatusBarHeightRootView(mDataBinding.getRoot());

        mDataBinding.rvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterFirst = new CategoryFirstAdapter();
        mDataBinding.rvLeft.setAdapter(mAdapterFirst);

        mDataBinding.rvRight.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterSecond=new CategorySecondAdapter();
        mDataBinding.rvRight.setAdapter(mAdapterSecond);

        mAdapterFirst.setmListener(new CategoryFirstAdapter.ItemCategoryFirstListener() {
            @Override
            public void onItemClick(List<ResCategory.ResFirstCategory.ResSecondCategory> secondCategory,String image) {
                if (!secondCategory.isEmpty()){
                    Log.i(TAG, "onItemClick: secondCategory:"+secondCategory.size());
                    mAdapterSecond.setDatas(secondCategory);
                }
                if (image != null && !image.isEmpty()) {
                    Log.i(TAG, "onItemClick: image:"+image);
                    //当image没值的时候，cardview会不可见，当下次有值的时候，这个不可见状态是要解除的，不然cardview不可见，image有值也不会看见图片
                    mDataBinding.cvImage.setVisibility(View.VISIBLE);
                    mViewModel.getUrl().setValue(image);
                }else {
                    mDataBinding.cvImage.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel.requestCategory(21);
        mViewModel.getDatas().observe(this, new Observer<List<ResCategory.ResFirstCategory>>() {
            @Override
            public void onChanged(List<ResCategory.ResFirstCategory> resFirstCategories) {
                if (!resFirstCategories.isEmpty()){
                    Log.i(TAG, "onChanged: 分类数据"+resFirstCategories.size());
                    mAdapterFirst.setDatas(resFirstCategories);
                    //页面一开始，就要对左边的recycleview初始化数据，将第一个分类的二级分类设置上
                    mAdapterSecond.setDatas(resFirstCategories.get(0).getChildren());
                    //页面一开始拿到数据，就要对页面初始化，将第一个分类的图片设置上
                    mViewModel.getUrl().setValue(resFirstCategories.get(0).getImage());
                }
            }
        });
    }
}
