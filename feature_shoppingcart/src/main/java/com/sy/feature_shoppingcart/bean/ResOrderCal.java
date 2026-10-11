package com.sy.feature_shoppingcart.bean;

import java.util.List;

public class ResOrderCal {
    /**
     * goods_original_amount : 3168.00
     * goods_old_amount : 2199.00
     * goods_amount : 2199.00
     * dispatch_amount : 0.00             // 运费金额
     * real_dispatch_amount : 0.00
     * order_amount : 2199.00             // 订单金额
     * pay_fee : 2149.00                  // 支付
     * temp_remain_pay_fee : 2149.00
     * total_discount_fee : 50.00
     * coupon_discount_fee : 0.00
     * promo_discount_fee : 50.00         // 活动优惠
     * money : 0.00
     * invoice_amount : 2149.00
     * activity_id : 0
     * activity_type : null
     * activity_title : null
     * promo_types : ["full_reduce"]
     * score_amount : 0
     * goods_list : [{"goods_id":"12","goods_num":1,"goods_sku_price_id":"20","current_sku_price":{"id":20,"goods_sku_ids":"16","goods_sku_text":["棕色"],"goods_id":12,"image":"","stock":15,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},"dispatch_type":"express","dispatch_type_text":"快递物流","goods":{"id":12,"type":"normal","title":"COACH蔻驰女包奢侈品官网奥莱山茶花Parker女士单肩包斜挎链条包","subtitle":"COACH蔻驰女包","category_ids":"4,10,114,22,115,116,33","image":"/uploads/20250226/f3db734d2aa534c1ddb7392828aa0a42.jpg","image_wh":{"w":"800","h":"800"},"images":["/uploads/20250226/f3db734d2aa534c1ddb7392828aa0a42.jpg","/uploads/20250226/ccf5dedadb3477070bab684190a9dcce.jpg","/uploads/20250226/e4d29ac990df660482da6c06f57e96b7.jpg"],"params":[{"title":"品牌","content":"COACH/蔻驰coach"},{"title":"质地","content":"PVC"},{"title":"闭合方式","content":"锁扣"},{"title":"图案","content":"条纹"},{"title":"风格","content":"欧美时尚"}],"original_price":"3168.00","price":["2199.00"],"is_sku":1,"limit_type":"none","limit_num":0,"likes":0,"views":114,"sales":1,"sales_show_type":"exact","stock_show_type":"exact","show_sales":0,"service_ids":[1,2,3,4],"dispatch_type":"express","dispatch_id":1,"is_offline":0,"status":"up","weigh":0,"createtime":"2023-03-09 17:10:33","updatetime":"2025-02-26 14:18:48","deletetime":null,"new_sku_prices":[{"id":19,"goods_sku_ids":"15","goods_sku_text":["白色"],"goods_id":12,"image":"","stock":16,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},{"id":20,"goods_sku_ids":"16","goods_sku_text":["棕色"],"goods_id":12,"image":"","stock":15,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},{"id":21,"goods_sku_ids":"17","goods_sku_text":["红色"],"goods_id":12,"image":"","stock":16,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"}],"stock":47,"activity_type":null,"status_text":"上架中","type_text":"实体商品","dispatch_type_text":"快递物流"},"activity_type":null,"goods_original_amount":"3168.00","goods_amount":"2199.00","score_amount":0,"weight":"0.00","original_dispatch_amount":"0.00","dispatch_amount":0,"dispatch_id":1,"promo_types":["full_reduce"],"dispatch_discount_fee":0,"promo_discount_fee":"50.00","coupon_discount_fee":0}]
     * promo_infos : [{"activity_id":"2","activity_title":"惊喜大促","activity_type":"full_reduce","activity_type_text":"满减","promo_discount_money":"50.00","promo_goods_amount":"2199.00","rule_type":"money","discount_rule":{"full":"300","discount":"50"},"goods_ids":["12"]}]
     * invoice_status : 1
     * invoice_amount_type : pay_fee
     * need_address : 1
     * offline_status : none
     * msg : null
     */

    private String goods_original_amount;
    private String goods_old_amount;
    private String goods_amount;
    private String dispatch_amount;
    private String real_dispatch_amount;
    private String order_amount;
    private String pay_fee;
    private String temp_remain_pay_fee;
    private String total_discount_fee;
    private String coupon_discount_fee;
    private String promo_discount_fee;
    private String money;
    private String invoice_amount;
    private int activity_id;
    private Object activity_type;
    private Object activity_title;
    private int score_amount;
    private int invoice_status;
    private String invoice_amount_type;
    private int need_address;
    private String offline_status;
    private Object msg;
    private List<String> promo_types;
    private List<GoodsListBean> goods_list;
    private List<PromoInfosBean> promo_infos;

    public String getGoods_original_amount() {
        return goods_original_amount;
    }

    public void setGoods_original_amount(String goods_original_amount) {
        this.goods_original_amount = goods_original_amount;
    }

    public String getGoods_old_amount() {
        return goods_old_amount;
    }

    public void setGoods_old_amount(String goods_old_amount) {
        this.goods_old_amount = goods_old_amount;
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

    public String getReal_dispatch_amount() {
        return real_dispatch_amount;
    }

    public void setReal_dispatch_amount(String real_dispatch_amount) {
        this.real_dispatch_amount = real_dispatch_amount;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getTemp_remain_pay_fee() {
        return temp_remain_pay_fee;
    }

    public void setTemp_remain_pay_fee(String temp_remain_pay_fee) {
        this.temp_remain_pay_fee = temp_remain_pay_fee;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
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

    public Object getActivity_title() {
        return activity_title;
    }

    public void setActivity_title(Object activity_title) {
        this.activity_title = activity_title;
    }

    public int getScore_amount() {
        return score_amount;
    }

    public void setScore_amount(int score_amount) {
        this.score_amount = score_amount;
    }

    public int getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(int invoice_status) {
        this.invoice_status = invoice_status;
    }

    public String getInvoice_amount_type() {
        return invoice_amount_type;
    }

    public void setInvoice_amount_type(String invoice_amount_type) {
        this.invoice_amount_type = invoice_amount_type;
    }

    public int getNeed_address() {
        return need_address;
    }

    public void setNeed_address(int need_address) {
        this.need_address = need_address;
    }

    public String getOffline_status() {
        return offline_status;
    }

    public void setOffline_status(String offline_status) {
        this.offline_status = offline_status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<String> getPromo_types() {
        return promo_types;
    }

    public void setPromo_types(List<String> promo_types) {
        this.promo_types = promo_types;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public List<PromoInfosBean> getPromo_infos() {
        return promo_infos;
    }

    public void setPromo_infos(List<PromoInfosBean> promo_infos) {
        this.promo_infos = promo_infos;
    }

    public static class GoodsListBean {
        /**
         * goods_id : 12
         * goods_num : 1
         * goods_sku_price_id : 20
         * current_sku_price : {"id":20,"goods_sku_ids":"16","goods_sku_text":["棕色"],"goods_id":12,"image":"","stock":15,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"}
         * dispatch_type : express
         * dispatch_type_text : 快递物流
         * goods : {"id":12,"type":"normal","title":"COACH蔻驰女包奢侈品官网奥莱山茶花Parker女士单肩包斜挎链条包","subtitle":"COACH蔻驰女包","category_ids":"4,10,114,22,115,116,33","image":"/uploads/20250226/f3db734d2aa534c1ddb7392828aa0a42.jpg","image_wh":{"w":"800","h":"800"},"images":["/uploads/20250226/f3db734d2aa534c1ddb7392828aa0a42.jpg","/uploads/20250226/ccf5dedadb3477070bab684190a9dcce.jpg","/uploads/20250226/e4d29ac990df660482da6c06f57e96b7.jpg"],"params":[{"title":"品牌","content":"COACH/蔻驰coach"},{"title":"质地","content":"PVC"},{"title":"闭合方式","content":"锁扣"},{"title":"图案","content":"条纹"},{"title":"风格","content":"欧美时尚"}],"original_price":"3168.00","price":["2199.00"],"is_sku":1,"limit_type":"none","limit_num":0,"likes":0,"views":114,"sales":1,"sales_show_type":"exact","stock_show_type":"exact","show_sales":0,"service_ids":[1,2,3,4],"dispatch_type":"express","dispatch_id":1,"is_offline":0,"status":"up","weigh":0,"createtime":"2023-03-09 17:10:33","updatetime":"2025-02-26 14:18:48","deletetime":null,"new_sku_prices":[{"id":19,"goods_sku_ids":"15","goods_sku_text":["白色"],"goods_id":12,"image":"","stock":16,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},{"id":20,"goods_sku_ids":"16","goods_sku_text":["棕色"],"goods_id":12,"image":"","stock":15,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},{"id":21,"goods_sku_ids":"17","goods_sku_text":["红色"],"goods_id":12,"image":"","stock":16,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"}],"stock":47,"activity_type":null,"status_text":"上架中","type_text":"实体商品","dispatch_type_text":"快递物流"}
         * activity_type : null
         * goods_original_amount : 3168.00
         * goods_amount : 2199.00
         * score_amount : 0
         * weight : 0.00
         * original_dispatch_amount : 0.00
         * dispatch_amount : 0
         * dispatch_id : 1
         * promo_types : ["full_reduce"]
         * dispatch_discount_fee : 0
         * promo_discount_fee : 50.00
         * coupon_discount_fee : 0
         */

        private String goods_id;
        private int goods_num;
        private String goods_sku_price_id;
        private CurrentSkuPriceBean current_sku_price;
        private String dispatch_type;
        private String dispatch_type_text;
        private GoodsBean goods;
        private Object activity_type;
        private String goods_original_amount;
        private String goods_amount;
        private int score_amount;
        private String weight;
        private String original_dispatch_amount;
        private int dispatch_amount;
        private int dispatch_id;
        private int dispatch_discount_fee;
        private String promo_discount_fee;
        private int coupon_discount_fee;
        private List<String> promo_types;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_sku_price_id() {
            return goods_sku_price_id;
        }

        public void setGoods_sku_price_id(String goods_sku_price_id) {
            this.goods_sku_price_id = goods_sku_price_id;
        }

        public CurrentSkuPriceBean getCurrent_sku_price() {
            return current_sku_price;
        }

        public void setCurrent_sku_price(CurrentSkuPriceBean current_sku_price) {
            this.current_sku_price = current_sku_price;
        }

        public String getDispatch_type() {
            return dispatch_type;
        }

        public void setDispatch_type(String dispatch_type) {
            this.dispatch_type = dispatch_type;
        }

        public String getDispatch_type_text() {
            return dispatch_type_text;
        }

        public void setDispatch_type_text(String dispatch_type_text) {
            this.dispatch_type_text = dispatch_type_text;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public Object getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(Object activity_type) {
            this.activity_type = activity_type;
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

        public int getScore_amount() {
            return score_amount;
        }

        public void setScore_amount(int score_amount) {
            this.score_amount = score_amount;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getOriginal_dispatch_amount() {
            return original_dispatch_amount;
        }

        public void setOriginal_dispatch_amount(String original_dispatch_amount) {
            this.original_dispatch_amount = original_dispatch_amount;
        }

        public int getDispatch_amount() {
            return dispatch_amount;
        }

        public void setDispatch_amount(int dispatch_amount) {
            this.dispatch_amount = dispatch_amount;
        }

        public int getDispatch_id() {
            return dispatch_id;
        }

        public void setDispatch_id(int dispatch_id) {
            this.dispatch_id = dispatch_id;
        }

        public int getDispatch_discount_fee() {
            return dispatch_discount_fee;
        }

        public void setDispatch_discount_fee(int dispatch_discount_fee) {
            this.dispatch_discount_fee = dispatch_discount_fee;
        }

        public String getPromo_discount_fee() {
            return promo_discount_fee;
        }

        public void setPromo_discount_fee(String promo_discount_fee) {
            this.promo_discount_fee = promo_discount_fee;
        }

        public int getCoupon_discount_fee() {
            return coupon_discount_fee;
        }

        public void setCoupon_discount_fee(int coupon_discount_fee) {
            this.coupon_discount_fee = coupon_discount_fee;
        }

        public List<String> getPromo_types() {
            return promo_types;
        }

        public void setPromo_types(List<String> promo_types) {
            this.promo_types = promo_types;
        }

        public static class CurrentSkuPriceBean {
            /**
             * id : 20
             * goods_sku_ids : 16
             * goods_sku_text : ["棕色"]
             * goods_id : 12
             * image :
             * stock : 15
             * stock_warning : null
             * sales : 1
             * sn :
             * weight : 0.00
             * cost_price : 1800.00
             * original_price : 3168.00
             * price : 2199.00
             * status : up
             * weigh : 0
             * createtime : 2022-10-13 15:59:25
             * updatetime : 2025-02-26 14:18:48
             * status_text : 上架
             */

            private int id;
            private String goods_sku_ids;
            private int goods_id;
            private String image;
            private int stock;
            private Object stock_warning;
            private int sales;
            private String sn;
            private String weight;
            private String cost_price;
            private String original_price;
            private String price;
            private String status;
            private int weigh;
            private String createtime;
            private String updatetime;
            private String status_text;
            private List<String> goods_sku_text;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoods_sku_ids() {
                return goods_sku_ids;
            }

            public void setGoods_sku_ids(String goods_sku_ids) {
                this.goods_sku_ids = goods_sku_ids;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public Object getStock_warning() {
                return stock_warning;
            }

            public void setStock_warning(Object stock_warning) {
                this.stock_warning = stock_warning;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getWeigh() {
                return weigh;
            }

            public void setWeigh(int weigh) {
                this.weigh = weigh;
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

            public String getStatus_text() {
                return status_text;
            }

            public void setStatus_text(String status_text) {
                this.status_text = status_text;
            }

            public List<String> getGoods_sku_text() {
                return goods_sku_text;
            }

            public void setGoods_sku_text(List<String> goods_sku_text) {
                this.goods_sku_text = goods_sku_text;
            }
        }

        public static class GoodsBean {
            /**
             * id : 12
             * type : normal
             * title : COACH蔻驰女包奢侈品官网奥莱山茶花Parker女士单肩包斜挎链条包
             * subtitle : COACH蔻驰女包
             * category_ids : 4,10,114,22,115,116,33
             * image : /uploads/20250226/f3db734d2aa534c1ddb7392828aa0a42.jpg
             * image_wh : {"w":"800","h":"800"}
             * images : ["/uploads/20250226/f3db734d2aa534c1ddb7392828aa0a42.jpg","/uploads/20250226/ccf5dedadb3477070bab684190a9dcce.jpg","/uploads/20250226/e4d29ac990df660482da6c06f57e96b7.jpg"]
             * params : [{"title":"品牌","content":"COACH/蔻驰coach"},{"title":"质地","content":"PVC"},{"title":"闭合方式","content":"锁扣"},{"title":"图案","content":"条纹"},{"title":"风格","content":"欧美时尚"}]
             * original_price : 3168.00
             * price : ["2199.00"]
             * is_sku : 1
             * limit_type : none
             * limit_num : 0
             * likes : 0
             * views : 114
             * sales : 1
             * sales_show_type : exact
             * stock_show_type : exact
             * show_sales : 0
             * service_ids : [1,2,3,4]
             * dispatch_type : express
             * dispatch_id : 1
             * is_offline : 0
             * status : up
             * weigh : 0
             * createtime : 2023-03-09 17:10:33
             * updatetime : 2025-02-26 14:18:48
             * deletetime : null
             * new_sku_prices : [{"id":19,"goods_sku_ids":"15","goods_sku_text":["白色"],"goods_id":12,"image":"","stock":16,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},{"id":20,"goods_sku_ids":"16","goods_sku_text":["棕色"],"goods_id":12,"image":"","stock":15,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"},{"id":21,"goods_sku_ids":"17","goods_sku_text":["红色"],"goods_id":12,"image":"","stock":16,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"1800.00","original_price":"3168.00","price":"2199.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:18:48","status_text":"上架"}]
             * stock : 47
             * activity_type : null
             * status_text : 上架中
             * type_text : 实体商品
             * dispatch_type_text : 快递物流
             */

            private int id;
            private String type;
            private String title;
            private String subtitle;
            private String category_ids;
            private String image;
            private ImageWhBean image_wh;
            private String original_price;
            private int is_sku;
            private String limit_type;
            private int limit_num;
            private int likes;
            private int views;
            private int sales;
            private String sales_show_type;
            private String stock_show_type;
            private int show_sales;
            private String dispatch_type;
            private int dispatch_id;
            private int is_offline;
            private String status;
            private int weigh;
            private String createtime;
            private String updatetime;
            private Object deletetime;
            private int stock;
            private Object activity_type;
            private String status_text;
            private String type_text;
            private String dispatch_type_text;
            private List<String> images;
            private List<ParamsBean> params;
            private List<String> price;
            private List<Integer> service_ids;
            private List<NewSkuPricesBean> new_sku_prices;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getCategory_ids() {
                return category_ids;
            }

            public void setCategory_ids(String category_ids) {
                this.category_ids = category_ids;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public ImageWhBean getImage_wh() {
                return image_wh;
            }

            public void setImage_wh(ImageWhBean image_wh) {
                this.image_wh = image_wh;
            }

            public String getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price;
            }

            public int getIs_sku() {
                return is_sku;
            }

            public void setIs_sku(int is_sku) {
                this.is_sku = is_sku;
            }

            public String getLimit_type() {
                return limit_type;
            }

            public void setLimit_type(String limit_type) {
                this.limit_type = limit_type;
            }

            public int getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(int limit_num) {
                this.limit_num = limit_num;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public String getSales_show_type() {
                return sales_show_type;
            }

            public void setSales_show_type(String sales_show_type) {
                this.sales_show_type = sales_show_type;
            }

            public String getStock_show_type() {
                return stock_show_type;
            }

            public void setStock_show_type(String stock_show_type) {
                this.stock_show_type = stock_show_type;
            }

            public int getShow_sales() {
                return show_sales;
            }

            public void setShow_sales(int show_sales) {
                this.show_sales = show_sales;
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

            public int getIs_offline() {
                return is_offline;
            }

            public void setIs_offline(int is_offline) {
                this.is_offline = is_offline;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getWeigh() {
                return weigh;
            }

            public void setWeigh(int weigh) {
                this.weigh = weigh;
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

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public Object getActivity_type() {
                return activity_type;
            }

            public void setActivity_type(Object activity_type) {
                this.activity_type = activity_type;
            }

            public String getStatus_text() {
                return status_text;
            }

            public void setStatus_text(String status_text) {
                this.status_text = status_text;
            }

            public String getType_text() {
                return type_text;
            }

            public void setType_text(String type_text) {
                this.type_text = type_text;
            }

            public String getDispatch_type_text() {
                return dispatch_type_text;
            }

            public void setDispatch_type_text(String dispatch_type_text) {
                this.dispatch_type_text = dispatch_type_text;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<ParamsBean> getParams() {
                return params;
            }

            public void setParams(List<ParamsBean> params) {
                this.params = params;
            }

            public List<String> getPrice() {
                return price;
            }

            public void setPrice(List<String> price) {
                this.price = price;
            }

            public List<Integer> getService_ids() {
                return service_ids;
            }

            public void setService_ids(List<Integer> service_ids) {
                this.service_ids = service_ids;
            }

            public List<NewSkuPricesBean> getNew_sku_prices() {
                return new_sku_prices;
            }

            public void setNew_sku_prices(List<NewSkuPricesBean> new_sku_prices) {
                this.new_sku_prices = new_sku_prices;
            }

            public static class ImageWhBean {
                /**
                 * w : 800
                 * h : 800
                 */

                private String w;
                private String h;

                public String getW() {
                    return w;
                }

                public void setW(String w) {
                    this.w = w;
                }

                public String getH() {
                    return h;
                }

                public void setH(String h) {
                    this.h = h;
                }
            }

            public static class ParamsBean {
                /**
                 * title : 品牌
                 * content : COACH/蔻驰coach
                 */

                private String title;
                private String content;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class NewSkuPricesBean {
                /**
                 * id : 19
                 * goods_sku_ids : 15
                 * goods_sku_text : ["白色"]
                 * goods_id : 12
                 * image :
                 * stock : 16
                 * stock_warning : null
                 * sales : 0
                 * sn :
                 * weight : 0.00
                 * cost_price : 1800.00
                 * original_price : 3168.00
                 * price : 2199.00
                 * status : up
                 * weigh : 0
                 * createtime : 2022-10-13 15:59:25
                 * updatetime : 2025-02-26 14:18:48
                 * status_text : 上架
                 */

                private int id;
                private String goods_sku_ids;
                private int goods_id;
                private String image;
                private int stock;
                private Object stock_warning;
                private int sales;
                private String sn;
                private String weight;
                private String cost_price;
                private String original_price;
                private String price;
                private String status;
                private int weigh;
                private String createtime;
                private String updatetime;
                private String status_text;
                private List<String> goods_sku_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getGoods_sku_ids() {
                    return goods_sku_ids;
                }

                public void setGoods_sku_ids(String goods_sku_ids) {
                    this.goods_sku_ids = goods_sku_ids;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public Object getStock_warning() {
                    return stock_warning;
                }

                public void setStock_warning(Object stock_warning) {
                    this.stock_warning = stock_warning;
                }

                public int getSales() {
                    return sales;
                }

                public void setSales(int sales) {
                    this.sales = sales;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getCost_price() {
                    return cost_price;
                }

                public void setCost_price(String cost_price) {
                    this.cost_price = cost_price;
                }

                public String getOriginal_price() {
                    return original_price;
                }

                public void setOriginal_price(String original_price) {
                    this.original_price = original_price;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public int getWeigh() {
                    return weigh;
                }

                public void setWeigh(int weigh) {
                    this.weigh = weigh;
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

                public String getStatus_text() {
                    return status_text;
                }

                public void setStatus_text(String status_text) {
                    this.status_text = status_text;
                }

                public List<String> getGoods_sku_text() {
                    return goods_sku_text;
                }

                public void setGoods_sku_text(List<String> goods_sku_text) {
                    this.goods_sku_text = goods_sku_text;
                }
            }
        }
    }

    public static class PromoInfosBean {
        /**
         * activity_id : 2
         * activity_title : 惊喜大促
         * activity_type : full_reduce
         * activity_type_text : 满减
         * promo_discount_money : 50.00
         * promo_goods_amount : 2199.00
         * rule_type : money
         * discount_rule : {"full":"300","discount":"50"}
         * goods_ids : ["12"]
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
