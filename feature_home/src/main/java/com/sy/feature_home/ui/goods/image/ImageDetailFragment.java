package com.sy.feature_home.ui.goods.image;

import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sy.feature_home.R;
import com.sy.feature_home.databinding.LayoutFragmentDetailImageBinding;
import com.sy.feature_home.databinding.LayoutFragmentImageBinding;
import com.sy.libbase.base.BaseFragment;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.config.ARouterPath;

@Route(path = ARouterPath.Home.FRAGMENT_IMAGE_DETAIL)
public class ImageDetailFragment extends BaseFragment<LayoutFragmentDetailImageBinding, BaseViewModel> {
    private static final String TAG = "ImageFragment";
    @Autowired(name = ARouterPath.Home.KEY_IMAGE_URL)
    public String mImageUrl;
    detailImageClickListener mListener;
    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return  R.layout.layout_fragment_detail_image;
    }

    @Override
    protected int getBindingVariableId() {
        return 0;
    }

    @Override
    protected void initView() {
        mDataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.ondetailImageClick();
            }
        });
    }

    @Override
    protected void initData() {
        mDataBinding.setImgUrl(mImageUrl);
    }

    public void setmListener(ImageDetailFragment.detailImageClickListener mListener) {
        this.mListener = mListener;
    }

    public interface detailImageClickListener{
        public void ondetailImageClick();
    }
}
