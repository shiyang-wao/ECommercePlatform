package com.sy.feature_user.bean;

public class ReqAddAddress {

    private String consignee;
    private String mobile;
    private String province_name;
    private String city_name;
    private String district_name;
    private String address;
    private boolean is_default;

    public ReqAddAddress(String consignee, String mobile, String province_name, String city_name, String district_name, String address, boolean is_default) {
        this.consignee = consignee;
        this.mobile = mobile;
        this.province_name = province_name;
        this.city_name = city_name;
        this.district_name = district_name;
        this.address = address;
        this.is_default = is_default;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    @Override
    public String toString() {
        return "ReqAddAddress{" +
                "consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province_name='" + province_name + '\'' +
                ", city_name='" + city_name + '\'' +
                ", district_name='" + district_name + '\'' +
                ", address='" + address + '\'' +
                ", is_default=" + is_default +
                '}';
    }
}
