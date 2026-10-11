package com.sy.feature_shoppingcart.bean;

import java.util.List;

public class ResCreatOrder {
    /**
     * id : 305
     * type : goods
     * order_sn : 202512464443187839001800
     * user_id : 18
     * activity_id : 0
     * activity_type : null
     * promo_types : full_reduce
     * goods_original_amount : 5380.00
     * goods_amount : 4498.00
     * dispatch_amount : 0.00
     * remark :
     * memo : null
     * status : unpaid
     * order_amount : 4498.00
     * score_amount : 0
     * pay_fee : 4448.00
     * original_pay_fee : 4448.00
     * remain_pay_fee : 4448.00
     * paid_time : null
     * pay_mode : online
     * apply_refund_status : 0
     * total_discount_fee : 50.00
     * coupon_discount_fee : 0.00
     * promo_discount_fee : 50.00
     * coupon_id : 0
     * invoice_status : 0
     * ext : {"promo_infos":[{"activity_id":"2","activity_title":"惊喜大促","activity_type":"full_reduce","activity_type_text":"满减","promo_discount_money":"50.00","promo_goods_amount":"4498.00","rule_type":"money","discount_rule":{"full":"300","discount":"50"},"goods_ids":["14"]}],"real_dispatch_amount":"0.00","activity_discount_amount":0,"offline_status":"none","need_address":1,"promo_discounts":{"full_reduce":"50.00","full_discount":"0.00","free_shipping":"0.00","full_gift":"0.00"},"expired_time":1759467704}
     * platform : H5
     * createtime : 2025-10-03 12:46:44
     * updatetime : 2025-10-03 12:46:44
     * deletetime : null
     * items : [{"id":336,"order_id":305,"user_id":18,"goods_id":14,"goods_type":"normal","goods_sku_price_id":38,"activity_id":0,"activity_type":null,"promo_types":"full_reduce","item_goods_sku_price_id":0,"goods_sku_text":"MateBook D 14 2022款,i5 16G 512G 皓月银","goods_title":"【国家补贴】华为 MateBook 14 酷睿Ultra 笔记本电脑 2.8K OLED 触控手写屏 轻薄灵动机身 AI电脑官方旗舰店","goods_image":"/uploads/20250226/574b97e1b319854931b86173f4accb0d.jpg","goods_original_price":"5380.00","goods_price":"4498.00","goods_num":1,"goods_weight":"0.00","discount_fee":"50.00","pay_fee":"4448.00","dispatch_status":0,"dispatch_fee":"0.00","dispatch_type":"express","dispatch_id":1,"aftersale_status":0,"comment_status":0,"refund_status":0,"refund_fee":"0.00","refund_msg":null,"order_express_id":0,"ext":{"original_dispatch_amount":"0.00","promo_discount_fee":"50.00","dispatch_discount_fee":0,"coupon_discount_fee":0,"activity_sku_price_ext":[],"ladder":[]},"createtime":"2025-10-03 12:46:44","updatetime":"2025-10-03 12:46:44","dispatch_status_text":"待发货","dispatch_type_text":"快递物流","aftersale_status_text":"未申请","refund_status_text":"未退款","comment_status_text":"待评价","status_code":"nosend","status_text":"待发货","status_desc":"等待卖家发货","btns":[],"activity_type_text":"","promo_types_text":["满减"]}]
     * type_text : 商城订单
     * status_code : unpaid
     * status_text : 待付款
     * status_desc : 等待买家付款
     * apply_refund_status_text : 未申请
     * btns : ["cancel","pay"]
     * platform_text : H5
     * activity_type_text :
     * promo_types_text : ["满减"]
     * wechat_extra_data : []
     */

    private int id;
    private String type;
    private String order_sn;
    private int user_id;
    private int activity_id;
    private Object activity_type;
    private String promo_types;
    private String goods_original_amount;
    private String goods_amount;
    private String dispatch_amount;
    private String remark;
    private Object memo;
    private String status;
    private String order_amount;
    private int score_amount;
    private String pay_fee;
    private String original_pay_fee;
    private String remain_pay_fee;
    private Object paid_time;
    private String pay_mode;
    private int apply_refund_status;
    private String total_discount_fee;
    private String coupon_discount_fee;
    private String promo_discount_fee;
    private int coupon_id;
    private int invoice_status;
    private ExtBean ext;
    private String platform;
    private String createtime;
    private String updatetime;
    private Object deletetime;
    private String type_text;
    private String status_code;
    private String status_text;
    private String status_desc;
    private String apply_refund_status_text;
    private String platform_text;
    private String activity_type_text;
    private List<ItemsBean> items;
    private List<String> btns;
    private List<String> promo_types_text;
    private List<?> wechat_extra_data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public Object getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(Object activity_type) {
        this.activity_type = activity_type;
    }

    public String getPromo_types() {
        return promo_types;
    }

    public void setPromo_types(String promo_types) {
        this.promo_types = promo_types;
    }

    public String getGoods_original_amount() {
        return goods_original_amount;
    }

    public void setGoods_original_amount(String goods_original_amount) {
        this.goods_original_amount = goods_original_amount;
    }

    public String getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(String goods_amount) {
        this.goods_amount = goods_amount;
    }

    public String getDispatch_amount() {
        return dispatch_amount;
    }

    public void setDispatch_amount(String dispatch_amount) {
        this.dispatch_amount = dispatch_amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getMemo() {
        return memo;
    }

    public void setMemo(Object memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public int getScore_amount() {
        return score_amount;
    }

    public void setScore_amount(int score_amount) {
        this.score_amount = score_amount;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getOriginal_pay_fee() {
        return original_pay_fee;
    }

    public void setOriginal_pay_fee(String original_pay_fee) {
        this.original_pay_fee = original_pay_fee;
    }

    public String getRemain_pay_fee() {
        return remain_pay_fee;
    }

    public void setRemain_pay_fee(String remain_pay_fee) {
        this.remain_pay_fee = remain_pay_fee;
    }

    public Object getPaid_time() {
        return paid_time;
    }

    public void setPaid_time(Object paid_time) {
        this.paid_time = paid_time;
    }

    public String getPay_mode() {
        return pay_mode;
    }

    public void setPay_mode(String pay_mode) {
        this.pay_mode = pay_mode;
    }

    public int getApply_refund_status() {
        return apply_refund_status;
    }

    public void setApply_refund_status(int apply_refund_status) {
        this.apply_refund_status = apply_refund_status;
    }

    public String getTotal_discount_fee() {
        return total_discount_fee;
    }

    public void setTotal_discount_fee(String total_discount_fee) {
        this.total_discount_fee = total_discount_fee;
    }

    public String getCoupon_discount_fee() {
        return coupon_discount_fee;
    }

    public void setCoupon_discount_fee(String coupon_discount_fee) {
        this.coupon_discount_fee = coupon_discount_fee;
    }

    public String getPromo_discount_fee() {
        return promo_discount_fee;
    }

    public void setPromo_discount_fee(String promo_discount_fee) {
        this.promo_discount_fee = promo_discount_fee;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(int invoice_status) {
        this.invoice_status = invoice_status;
    }

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Object getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Object deletetime) {
        this.deletetime = deletetime;
    }

    public String getType_text() {
        return type_text;
    }

    public void setType_text(String type_text) {
        this.type_text = type_text;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public String getStatus_desc() {
        return status_desc;
    }

    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc;
    }

    public String getApply_refund_status_text() {
        return apply_refund_status_text;
    }

    public void setApply_refund_status_text(String apply_refund_status_text) {
        this.apply_refund_status_text = apply_refund_status_text;
    }

    public String getPlatform_text() {
        return platform_text;
    }

    public void setPlatform_text(String platform_text) {
        this.platform_text = platform_text;
    }

    public String getActivity_type_text() {
        return activity_type_text;
    }

    public void setActivity_type_text(String activity_type_text) {
        this.activity_type_text = activity_type_text;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }

    public List<String> getPromo_types_text() {
        return promo_types_text;
    }

    public void setPromo_types_text(List<String> promo_types_text) {
        this.promo_types_text = promo_types_text;
    }

    public List<?> getWechat_extra_data() {
        return wechat_extra_data;
    }

    public void setWechat_extra_data(List<?> wechat_extra_data) {
        this.wechat_extra_data = wechat_extra_data;
    }

    public static class ExtBean {
        /**
         * promo_infos : [{"activity_id":"2","activity_title":"惊喜大促","activity_type":"full_reduce","activity_type_text":"满减","promo_discount_money":"50.00","promo_goods_amount":"4498.00","rule_type":"money","discount_rule":{"full":"300","discount":"50"},"goods_ids":["14"]}]
         * real_dispatch_amount : 0.00
         * activity_discount_amount : 0
         * offline_status : none
         * need_address : 1
         * promo_discounts : {"full_reduce":"50.00","full_discount":"0.00","free_shipping":"0.00","full_gift":"0.00"}
         * expired_time : 1759467704
         */

        private String real_dispatch_amount;
        private int activity_discount_amount;
        private String offline_status;
        private int need_address;
        private PromoDiscountsBean promo_discounts;
        private int expired_time;
        private List<PromoInfosBean> promo_infos;

        public String getReal_dispatch_amount() {
            return real_dispatch_amount;
        }

        public void setReal_dispatch_amount(String real_dispatch_amount) {
            this.real_dispatch_amount = real_dispatch_amount;
        }

        public int getActivity_discount_amount() {
            return activity_discount_amount;
        }

        public void setActivity_discount_amount(int activity_discount_amount) {
            this.activity_discount_amount = activity_discount_amount;
        }

        public String getOffline_status() {
            return offline_status;
        }

        public void setOffline_status(String offline_status) {
            this.offline_status = offline_status;
        }

        public int getNeed_address() {
            return need_address;
        }

        public void setNeed_address(int need_address) {
            this.need_address = need_address;
        }

        public PromoDiscountsBean getPromo_discounts() {
            return promo_discounts;
        }

        public void setPromo_discounts(PromoDiscountsBean promo_discounts) {
            this.promo_discounts = promo_discounts;
        }

        public int getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(int expired_time) {
            this.expired_time = expired_time;
        }

        public List<PromoInfosBean> getPromo_infos() {
            return promo_infos;
        }

        public void setPromo_infos(List<PromoInfosBean> promo_infos) {
            this.promo_infos = promo_infos;
        }

        public static class PromoDiscountsBean {
            /**
             * full_reduce : 50.00
             * full_discount : 0.00
             * free_shipping : 0.00
             * full_gift : 0.00
             */

            private String full_reduce;
            private String full_discount;
            private String free_shipping;
            private String full_gift;

            public String getFull_reduce() {
                return full_reduce;
            }

            public void setFull_reduce(String full_reduce) {
                this.full_reduce = full_reduce;
            }

            public String getFull_discount() {
                return full_discount;
            }

            public void setFull_discount(String full_discount) {
                this.full_discount = full_discount;
            }

            public String getFree_shipping() {
                return free_shipping;
            }

            public void setFree_shipping(String free_shipping) {
                this.free_shipping = free_shipping;
            }

            public String getFull_gift() {
                return full_gift;
            }

            public void setFull_gift(String full_gift) {
                this.full_gift = full_gift;
            }
        }

        public static class PromoInfosBean {
            /**
             * activity_id : 2
             * activity_title : 惊喜大促
             * activity_type : full_reduce
             * activity_type_text : 满减
             * promo_discount_money : 50.00
             * promo_goods_amount : 4498.00
             * rule_type : money
             * discount_rule : {"full":"300","discount":"50"}
             * goods_ids : ["14"]
             */

            private String activity_id;
            private String activity_title;
            private String activity_type;
            private String activity_type_text;
            private String promo_discount_money;
            private String promo_goods_amount;
            private String rule_type;
            private DiscountRuleBean discount_rule;
            private List<String> goods_ids;

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getActivity_title() {
                return activity_title;
            }

            public void setActivity_title(String activity_title) {
                this.activity_title = activity_title;
            }

            public String getActivity_type() {
                return activity_type;
            }

            public void setActivity_type(String activity_type) {
                this.activity_type = activity_type;
            }

            public String getActivity_type_text() {
                return activity_type_text;
            }

            public void setActivity_type_text(String activity_type_text) {
                this.activity_type_text = activity_type_text;
            }

            public String getPromo_discount_money() {
                return promo_discount_money;
            }

            public void setPromo_discount_money(String promo_discount_money) {
                this.promo_discount_money = promo_discount_money;
            }

            public String getPromo_goods_amount() {
                return promo_goods_amount;
            }

            public void setPromo_goods_amount(String promo_goods_amount) {
                this.promo_goods_amount = promo_goods_amount;
            }

            public String getRule_type() {
                return rule_type;
            }

            public void setRule_type(String rule_type) {
                this.rule_type = rule_type;
            }

            public DiscountRuleBean getDiscount_rule() {
                return discount_rule;
            }

            public void setDiscount_rule(DiscountRuleBean discount_rule) {
                this.discount_rule = discount_rule;
            }

            public List<String> getGoods_ids() {
                return goods_ids;
            }

            public void setGoods_ids(List<String> goods_ids) {
                this.goods_ids = goods_ids;
            }

            public static class DiscountRuleBean {
                /**
                 * full : 300
                 * discount : 50
                 */

                private String full;
                private String discount;

                public String getFull() {
                    return full;
                }

                public void setFull(String full) {
                    this.full = full;
                }

                public String getDiscount() {
                    return discount;
                }

                public void setDiscount(String discount) {
                    this.discount = discount;
                }
            }
        }
    }

    public static class ItemsBean {
        /**
         * id : 336
         * order_id : 305
         * user_id : 18
         * goods_id : 14
         * goods_type : normal
         * goods_sku_price_id : 38
         * activity_id : 0
         * activity_type : null
         * promo_types : full_reduce
         * item_goods_sku_price_id : 0
         * goods_sku_text : MateBook D 14 2022款,i5 16G 512G 皓月银
         * goods_title : 【国家补贴】华为 MateBook 14 酷睿Ultra 笔记本电脑 2.8K OLED 触控手写屏 轻薄灵动机身 AI电脑官方旗舰店
         * goods_image : /uploads/20250226/574b97e1b319854931b86173f4accb0d.jpg
         * goods_original_price : 5380.00
         * goods_price : 4498.00
         * goods_num : 1
         * goods_weight : 0.00
         * discount_fee : 50.00
         * pay_fee : 4448.00
         * dispatch_status : 0
         * dispatch_fee : 0.00
         * dispatch_type : express
         * dispatch_id : 1
         * aftersale_status : 0
         * comment_status : 0
         * refund_status : 0
         * refund_fee : 0.00
         * refund_msg : null
         * order_express_id : 0
         * ext : {"original_dispatch_amount":"0.00","promo_discount_fee":"50.00","dispatch_discount_fee":0,"coupon_discount_fee":0,"activity_sku_price_ext":[],"ladder":[]}
         * createtime : 2025-10-03 12:46:44
         * updatetime : 2025-10-03 12:46:44
         * dispatch_status_text : 待发货
         * dispatch_type_text : 快递物流
         * aftersale_status_text : 未申请
         * refund_status_text : 未退款
         * comment_status_text : 待评价
         * status_code : nosend
         * status_text : 待发货
         * status_desc : 等待卖家发货
         * btns : []
         * activity_type_text :
         * promo_types_text : ["满减"]
         */

        private int id;
        private int order_id;
        private int user_id;
        private int goods_id;
        private String goods_type;
        private int goods_sku_price_id;
        private int activity_id;
        private Object activity_type;
        private String promo_types;
        private int item_goods_sku_price_id;
        private String goods_sku_text;
        private String goods_title;
        private String goods_image;
        private String goods_original_price;
        private String goods_price;
        private int goods_num;
        private String goods_weight;
        private String discount_fee;
        private String pay_fee;
        private int dispatch_status;
        private String dispatch_fee;
        private String dispatch_type;
        private int dispatch_id;
        private int aftersale_status;
        private int comment_status;
        private int refund_status;
        private String refund_fee;
        private Object refund_msg;
        private int order_express_id;
        private ExtBeanX ext;
        private String createtime;
        private String updatetime;
        private String dispatch_status_text;
        private String dispatch_type_text;
        private String aftersale_status_text;
        private String refund_status_text;
        private String comment_status_text;
        private String status_code;
        private String status_text;
        private String status_desc;
        private String activity_type_text;
        private List<?> btns;
        private List<String> promo_types_text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public int getGoods_sku_price_id() {
            return goods_sku_price_id;
        }

        public void setGoods_sku_price_id(int goods_sku_price_id) {
            this.goods_sku_price_id = goods_sku_price_id;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public Object getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(Object activity_type) {
            this.activity_type = activity_type;
        }

        public String getPromo_types() {
            return promo_types;
        }

        public void setPromo_types(String promo_types) {
            this.promo_types = promo_types;
        }

        public int getItem_goods_sku_price_id() {
            return item_goods_sku_price_id;
        }

        public void setItem_goods_sku_price_id(int item_goods_sku_price_id) {
            this.item_goods_sku_price_id = item_goods_sku_price_id;
        }

        public String getGoods_sku_text() {
            return goods_sku_text;
        }

        public void setGoods_sku_text(String goods_sku_text) {
            this.goods_sku_text = goods_sku_text;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_original_price() {
            return goods_original_price;
        }

        public void setGoods_original_price(String goods_original_price) {
            this.goods_original_price = goods_original_price;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_weight() {
            return goods_weight;
        }

        public void setGoods_weight(String goods_weight) {
            this.goods_weight = goods_weight;
        }

        public String getDiscount_fee() {
            return discount_fee;
        }

        public void setDiscount_fee(String discount_fee) {
            this.discount_fee = discount_fee;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public int getDispatch_status() {
            return dispatch_status;
        }

        public void setDispatch_status(int dispatch_status) {
            this.dispatch_status = dispatch_status;
        }

        public String getDispatch_fee() {
            return dispatch_fee;
        }

        public void setDispatch_fee(String dispatch_fee) {
            this.dispatch_fee = dispatch_fee;
        }

        public String getDispatch_type() {
            return dispatch_type;
        }

        public void setDispatch_type(String dispatch_type) {
            this.dispatch_type = dispatch_type;
        }

        public int getDispatch_id() {
            return dispatch_id;
        }

        public void setDispatch_id(int dispatch_id) {
            this.dispatch_id = dispatch_id;
        }

        public int getAftersale_status() {
            return aftersale_status;
        }

        public void setAftersale_status(int aftersale_status) {
            this.aftersale_status = aftersale_status;
        }

        public int getComment_status() {
            return comment_status;
        }

        public void setComment_status(int comment_status) {
            this.comment_status = comment_status;
        }

        public int getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(int refund_status) {
            this.refund_status = refund_status;
        }

        public String getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(String refund_fee) {
            this.refund_fee = refund_fee;
        }

        public Object getRefund_msg() {
            return refund_msg;
        }

        public void setRefund_msg(Object refund_msg) {
            this.refund_msg = refund_msg;
        }

        public int getOrder_express_id() {
            return order_express_id;
        }

        public void setOrder_express_id(int order_express_id) {
            this.order_express_id = order_express_id;
        }

        public ExtBeanX getExt() {
            return ext;
        }

        public void setExt(ExtBeanX ext) {
            this.ext = ext;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getDispatch_status_text() {
            return dispatch_status_text;
        }

        public void setDispatch_status_text(String dispatch_status_text) {
            this.dispatch_status_text = dispatch_status_text;
        }

        public String getDispatch_type_text() {
            return dispatch_type_text;
        }

        public void setDispatch_type_text(String dispatch_type_text) {
            this.dispatch_type_text = dispatch_type_text;
        }

        public String getAftersale_status_text() {
            return aftersale_status_text;
        }

        public void setAftersale_status_text(String aftersale_status_text) {
            this.aftersale_status_text = aftersale_status_text;
        }

        public String getRefund_status_text() {
            return refund_status_text;
        }

        public void setRefund_status_text(String refund_status_text) {
            this.refund_status_text = refund_status_text;
        }

        public String getComment_status_text() {
            return comment_status_text;
        }

        public void setComment_status_text(String comment_status_text) {
            this.comment_status_text = comment_status_text;
        }

        public String getStatus_code() {
            return status_code;
        }

        public void setStatus_code(String status_code) {
            this.status_code = status_code;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public String getStatus_desc() {
            return status_desc;
        }

        public void setStatus_desc(String status_desc) {
            this.status_desc = status_desc;
        }

        public String getActivity_type_text() {
            return activity_type_text;
        }

        public void setActivity_type_text(String activity_type_text) {
            this.activity_type_text = activity_type_text;
        }

        public List<?> getBtns() {
            return btns;
        }

        public void setBtns(List<?> btns) {
            this.btns = btns;
        }

        public List<String> getPromo_types_text() {
            return promo_types_text;
        }

        public void setPromo_types_text(List<String> promo_types_text) {
            this.promo_types_text = promo_types_text;
        }

        public static class ExtBeanX {
            /**
             * original_dispatch_amount : 0.00
             * promo_discount_fee : 50.00
             * dispatch_discount_fee : 0
             * coupon_discount_fee : 0
             * activity_sku_price_ext : []
             * ladder : []
             */

            private String original_dispatch_amount;
            private String promo_discount_fee;
            private int dispatch_discount_fee;
            private int coupon_discount_fee;
            private List<?> activity_sku_price_ext;
            private List<?> ladder;

            public String getOriginal_dispatch_amount() {
                return original_dispatch_amount;
            }

            public void setOriginal_dispatch_amount(String original_dispatch_amount) {
                this.original_dispatch_amount = original_dispatch_amount;
            }

            public String getPromo_discount_fee() {
                return promo_discount_fee;
            }

            public void setPromo_discount_fee(String promo_discount_fee) {
                this.promo_discount_fee = promo_discount_fee;
            }

            public int getDispatch_discount_fee() {
                return dispatch_discount_fee;
            }

            public void setDispatch_discount_fee(int dispatch_discount_fee) {
                this.dispatch_discount_fee = dispatch_discount_fee;
            }

            public int getCoupon_discount_fee() {
                return coupon_discount_fee;
            }

            public void setCoupon_discount_fee(int coupon_discount_fee) {
                this.coupon_discount_fee = coupon_discount_fee;
            }

            public List<?> getActivity_sku_price_ext() {
                return activity_sku_price_ext;
            }

            public void setActivity_sku_price_ext(List<?> activity_sku_price_ext) {
                this.activity_sku_price_ext = activity_sku_price_ext;
            }

            public List<?> getLadder() {
                return ladder;
            }

            public void setLadder(List<?> ladder) {
                this.ladder = ladder;
            }
        }
    }
}
