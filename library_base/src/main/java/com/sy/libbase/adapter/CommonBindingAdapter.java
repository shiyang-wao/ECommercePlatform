package com.sy.libbase.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.sy.libbase.R;

public class CommonBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageview,String url ){
        if(url!=null&&!url.isEmpty()){
            Glide.with(imageview.getContext())
                    .load(url)
                    .error(R.mipmap.icon_default)
                    .into(imageview);
        }

    }
    @BindingAdapter("imageCircleUrl")
    public static void loadCirleImage(ImageView imageview,String url){
        Glide.with(imageview.getContext())
                .load(url)
                .placeholder(R.mipmap.icon_default_avatar) //加载过程中或者没有加载的时候的占位图
                .error(R.mipmap.icon_default_avatar) //加载错误的占位图
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))//加载圆形图片
                .into(imageview);
    }

    /**
     * 本地图片
     *
     * @param imageView
     * @param url
     */
    @BindingAdapter("imageCircleUrl")
    public static void loadCircleImage(ImageView imageView, Uri url) {
        if (url != null) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .placeholder(R.mipmap.icon_default_avatar)//加载过程的占位图
                    .error(R.mipmap.icon_default_avatar)//加载失败时候的占位图
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))//加载圆形图片
                    .into(imageView);
        }
    }

    // 自定义属性：app:goodsCountText，接收商品数量并处理文本
    @BindingAdapter("goodsCountText")
    public static void setGoodsCountText(TextView textView, int count) {
        // 1. 生成完整文本（例如“共3件商品”）
        String fullText = "共" + count + "件商品";
        SpannableString spannable = new SpannableString(fullText);

        // 2. 计算数字部分的起始和结束索引（“共”是第0位，数字从第1位开始）
        String countStr = String.valueOf(count);
        int start = 1; // 数字起始位置（跳过“共”）
        int end = start + countStr.length(); // 数字结束位置（起始+数字长度）

        // 3. 给数字部分设置颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#F77422"));
        spannable.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 4. 设置最终文本到TextView
        textView.setText(spannable);
    }
}
