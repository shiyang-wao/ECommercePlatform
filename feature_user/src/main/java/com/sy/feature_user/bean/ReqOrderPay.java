package com.sy.feature_user.bean;

public class ReqOrderPay {

    /**
     * order_sn : 202504260127899160018000
     * payment : money
     */

    private String order_sn;
    private String payment;

    public ReqOrderPay(String order_sn, String payment) {
        this.order_sn = order_sn;
        this.payment = payment;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
