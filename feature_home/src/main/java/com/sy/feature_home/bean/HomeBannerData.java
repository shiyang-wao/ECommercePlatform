package com.sy.feature_home.bean;

import com.stx.xhb.androidx.entity.BaseBannerInfo;

/**
 * xBanner 需要的特殊数据格式
 */
public class HomeBannerData implements BaseBannerInfo {

    private  String imgUrl;

    public HomeBannerData(String imgUrl) {
        this.imgUrl = "https://shop.example.com/"+imgUrl;
    }

    @Override
    public Object getXBannerUrl() {
        return imgUrl;
    }

    @Override
    public String getXBannerTitle() {
        return null;
    }
}
