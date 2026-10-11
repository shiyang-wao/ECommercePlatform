package com.sy.feature_home.ui.goods.image;

import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import com.sy.feature_home.R;
import com.sy.feature_home.databinding.LayoutFragmentImageBinding;
import com.sy.libbase.base.BaseFragment;
import com.sy.libbase.base.BaseViewModel;
import com.sy.libbase.config.ARouterPath;

@Route(path = ARouterPath.Home.FRAGMENT_IMAGE)
public class ImageFragment extends BaseFragment<LayoutFragmentImageBinding, BaseViewModel> {
    private static final String TAG = "ImageFragment";
    @Autowired(name = ARouterPath.Home.KEY_IMAGE_URL)
    public String mImageUrl;
    @Autowired(name = ARouterPath.Home.KEY_IMAGE_POSITION)
    public int mImagePosition;

    imageClickListener mListener;

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return  R.layout.layout_fragment_image;
    }

    @Override
    protected int getBindingVariableId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mDataBinding.setImgUrl(mImageUrl);
        mDataBinding.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: 当前位置"+mImagePosition);
                    mListener.onImageClick(mImagePosition);
            }
        });
    }

    public void setmListener(imageClickListener mListener) {
        this.mListener = mListener;
    }

    public interface imageClickListener{
        public void onImageClick(int position);
    }
}
