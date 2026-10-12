package com.sy.feature_home.adapter;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.androidx.XBanner;
import com.sy.feature_home.bean.HomeBannerData;
import com.sy.feature_home.bean.ResGoodsDetail;
import com.sy.feature_home.databinding.ItemBannerBinding;
import com.sy.feature_home.databinding.ItemFeaturedBinding;
import com.sy.feature_home.databinding.ItemHotsaleBinding;
import com.sy.feature_home.databinding.ItemImageBinding;
import com.sy.network.bean.ResHomeItem;

import java.util.ArrayList;
import java.util.List;

public class HomeDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomeData1Adapter";
    public static final int ITEM_TYPE_BANNER = 1; //banner类型的item，就上面呢个跟轮播图样式的
    public static final int ITEM_TYPE_IMAGE = 2; //圆形和方形一起的item，就中间偏上的部分
    public static final int ITEM_TYPE_FEATURED = 3; //三个商品一起的item
    public static final int ITEM_TYPE_HOTSALE = 4; //六个商品网格排列的item
    private ArrayList<HomeBannerData> mBannerDatas = new ArrayList<>(); //banner类型数据
    private List<ResHomeItem.MenuButtonItem> mMenuItemDatass = new ArrayList<>(); //圆型图片的数据类型
    private List<ResHomeItem.ImageCubeItem> mCubeItemDatas = new ArrayList<>(); //方形图片的数据
    private List<ResGoodsDetail> mFeaturedDatas = new ArrayList<>(); //精选榜单的数据
    private List<ResGoodsDetail> mHotsaleDatas = new ArrayList<>(); //热销商品的数据
    private static final String srchttp="https://shop.example.com/";
    private ItemHotsaleListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_TYPE_BANNER) {
            ItemBannerBinding bannerBinding = ItemBannerBinding.inflate(inflater, parent, false);
            return new BannerViewHoldel(bannerBinding);
        } else if (viewType == ITEM_TYPE_IMAGE) {
            ItemImageBinding imageBinding = ItemImageBinding.inflate(inflater, parent, false);
            return new ImageViewHoldel(imageBinding);
        } else if (viewType == ITEM_TYPE_FEATURED) {
            ItemFeaturedBinding featuredBinding = ItemFeaturedBinding.inflate(inflater, parent, false);
            return new FeaturedViewHoldel(featuredBinding);
        } else {
            ItemHotsaleBinding hotsaleBinding = ItemHotsaleBinding.inflate(inflater, parent, false);
            return new HotsaleViewHoldel(hotsaleBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM_TYPE_BANNER) {
            BannerViewHoldel viewHoldel = (BannerViewHoldel) holder;
            ItemBannerBinding binding = viewHoldel.bannerBinding;
            binding.xbanner.setBannerData(mBannerDatas);
            binding.xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    // 将model强转为你的数据类型
                    HomeBannerData bannerData = (HomeBannerData) model;
                    // 使用Glide加载图片
                    Glide.with(view.getContext())
                            .load(bannerData.getXBannerUrl())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(22))) //加载的时候圆角会有延迟
                            .into((ImageView) view);
                }
            });
        } else if (viewType == ITEM_TYPE_IMAGE) {
            ImageViewHoldel viewHoldel = (ImageViewHoldel) holder;
            viewHoldel.imageBinding.setMenuButtonDatas(mMenuItemDatass);
            viewHoldel.imageBinding.setImageCubeDatas(mCubeItemDatas);
            viewHoldel.imageBinding.setSrchttp(srchttp);
        } else if (viewType == ITEM_TYPE_FEATURED) {
            FeaturedViewHoldel viewHoldel=(FeaturedViewHoldel) holder;
            viewHoldel.featuredBinding.setFeaturedDatas(mFeaturedDatas);
            viewHoldel.featuredBinding.setSrchttp(srchttp);
            viewHoldel.featuredBinding.setGoods1Sale("已售"+mFeaturedDatas.get(0).getSales());
            viewHoldel.featuredBinding.setGoods2Sale("已售"+mFeaturedDatas.get(1).getSales());
            viewHoldel.featuredBinding.setGoods3Sale("已售"+mFeaturedDatas.get(2).getSales());

        } else {
            HotsaleViewHoldel viewHoldel =(HotsaleViewHoldel) holder;
            viewHoldel.hotsaleBinding.setHotsaleDatas(mHotsaleDatas.get(position-3));
            viewHoldel.hotsaleBinding.setSrchttp(srchttp);
            viewHoldel.hotsaleBinding.setGoodsSale("已售"+mHotsaleDatas.get(position-3).getSales());
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mBannerDatas.size() > 0) {
            count++;
        }
        if (mMenuItemDatass.size() > 0 && mCubeItemDatas.size() > 0) {
            count++;
        }
        if (!mFeaturedDatas.isEmpty()){
            count++;
        }
        if (!mHotsaleDatas.isEmpty()){
            count+=mHotsaleDatas.size();
        }
        Log.i(TAG, "getItemCount: count:" + count);
        return count;
    }

    public int getItemViewType(int position) {
        if (position==0){
            return ITEM_TYPE_BANNER;
        } else if (position==1) {
            return ITEM_TYPE_IMAGE;
        } else if (position==2) {
            return ITEM_TYPE_FEATURED;
        }else {
            return ITEM_TYPE_HOTSALE;
        }
    }

    public void setDatas(List<ResHomeItem> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        for (ResHomeItem item : datas) {
            switch (item.getType()) {
                case "imageBanner":
                    ResHomeItem.ImageBannerData bannerData = (ResHomeItem.ImageBannerData) item.getData();
                    List<ResHomeItem.BannerItem> banners = bannerData.getList();
                    mBannerDatas = convertXBannerDatas(banners);
                    Log.i(TAG, "setDatas: mBannerDatas.size" + mBannerDatas.size());
                    break;
                case "menuButton":
                    ResHomeItem.MenuButtonData menuData = (ResHomeItem.MenuButtonData) item.getData();
                    mMenuItemDatass = menuData.getList();
                    Log.i(TAG, "onSuccess: menu数量：" + mMenuItemDatass.size());
                    break;
                case "imageCube":
                    ResHomeItem.ImageCubeData cubeData = (ResHomeItem.ImageCubeData) item.getData();
                    mCubeItemDatas = cubeData.getList();
                    Log.i(TAG, "onSuccess: cube数量：" + mCubeItemDatas.size());
                    break;
                default:
                    // 处理未知类型或记录日志
                    Log.w(TAG, "其他的组件类型: " + item.getType());
                    break;
            }
        }
        //改变适配器的数据一定要刷新
        notifyDataSetChanged();
    }

    public void setGoodsDatas(List<ResGoodsDetail> datas) {
        if (datas.size() == 3) {
            mFeaturedDatas = datas;
        } else {
            mHotsaleDatas=datas;
        }
    }

    /**
     * 因为使用了一个XBanner的自定义View，它需要接受特定的数据类型，所以要把服务端返回的数据转成它可以接受的数据类型
     *
     * @param data
     * @return
     */
    private ArrayList<HomeBannerData> convertXBannerDatas(List<ResHomeItem.BannerItem> data) {
        Log.i(TAG, "convertXBannerDatas: data.size" + data.size());
        if (data != null && data.size() > 0) {
            ArrayList<HomeBannerData> xBannerDatas = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                ResHomeItem.BannerItem detail = data.get(i);
                HomeBannerData bannerData = new HomeBannerData(detail.getSrc());
                xBannerDatas.add(bannerData);
            }
            return xBannerDatas;
        }
        return null;
    }

    public class BannerViewHoldel extends RecyclerView.ViewHolder {

        private final ItemBannerBinding bannerBinding;

        public BannerViewHoldel(ItemBannerBinding binding) {
            super(binding.getRoot());
            this.bannerBinding = binding;
        }
    }

    public class ImageViewHoldel extends RecyclerView.ViewHolder {
        private final ItemImageBinding imageBinding;

        public ImageViewHoldel(ItemImageBinding binding) {
            super(binding.getRoot());
            this.imageBinding = binding;
        }
    }

    public class FeaturedViewHoldel extends RecyclerView.ViewHolder {
        private final ItemFeaturedBinding featuredBinding;

        public FeaturedViewHoldel(ItemFeaturedBinding binding) {
            super(binding.getRoot());
            this.featuredBinding = binding;
            binding.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//设置原价的删除线
            binding.tvOriginalPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvOriginalPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
    public class HotsaleViewHoldel extends RecyclerView.ViewHolder {
        private final ItemHotsaleBinding hotsaleBinding;

        public HotsaleViewHoldel(ItemHotsaleBinding binding) {
            super(binding.getRoot());
            this.hotsaleBinding = binding;
            binding.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//设置原价的删除线
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    Log.i(TAG, "onClick: position"+position);
                    int id = mHotsaleDatas.get(position-3).getId();
                    mListener.onItemClick(id);
                }
            });
        }
    }

    public void setmListener(ItemHotsaleListener mListener) {
        this.mListener = mListener;
    }

    public interface ItemHotsaleListener {

        void onItemClick(int goodsId);

    }
}
