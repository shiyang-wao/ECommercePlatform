package com.sy.feature_home.bean;

import java.util.List;

public class ResGoodsDetail {


    /**
     * id : 17
     * type : normal
     * title : 漫步者(EDIFIER) HECATE G2专业版 USB7.1声道 游戏耳机电竞耳麦头戴式电脑耳机麦克风吃鸡耳机带线控 黑色
     * subtitle : 麦克风吃鸡耳机带线控
     * category_ids : 8,62,64,86,91,92,35
     * image : /uploads/20250225/e6ac4da16eeb8f286539a0a109b2bc0d.jpg
     * image_wh : {"w":"800","h":"800"}
     * images : ["/uploads/20250225/e6ac4da16eeb8f286539a0a109b2bc0d.jpg","/uploads/20250225/8d8498f0b69adb3244fefd00c27d01e3.jpg","/uploads/20250225/9b23806b112aabb48fdb33ca54585dc2.jpg"]
     * params : [{"title":"频响范围","content":"20Hz-20KHz"},{"title":"线型","content":"单边导线"},{"title":"振膜尺寸","content":"50mm"}]
     * original_price : 389.00
     * price : ["299.00"]
     * is_sku : 1
     * limit_type : none
     * limit_num : 0
     * likes : 0
     * views : 1344
     * sales : 7
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
     * updatetime : 2025-07-14 15:33:33
     * deletetime : null
     * new_sku_prices : [{"id":45,"goods_sku_ids":"42","goods_sku_text":["白色"],"goods_id":17,"image":"","stock":1019,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"120.00","original_price":"389.00","price":"299.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-07-14 15:33:33","status_text":"上架"},{"id":46,"goods_sku_ids":"43","goods_sku_text":["黑色"],"goods_id":17,"image":"","stock":1027,"stock_warning":null,"sales":5,"sn":"","weight":"0.00","cost_price":"120.00","original_price":"389.00","price":"299.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-07-14 15:33:33","status_text":"上架"},{"id":47,"goods_sku_ids":"44","goods_sku_text":["黄色"],"goods_id":17,"image":"","stock":1019,"stock_warning":null,"sales":1,"sn":"","weight":"0.00","cost_price":"120.00","original_price":"389.00","price":"299.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-07-14 15:33:33","status_text":"上架"}]
     * stock : 3065
     * activity_type : null
     * activities : [{"title":"惊喜秒杀","type_text":"秒杀","prehead_time":"1741504692","richtext_title":"","rules":{"is_commission":"1","is_free_shipping":"1","sales_show_type":"real","limit_num":"1","order_auto_close":"5"},"type":"seckill","id":"1","goods_ids":"21,18,17","classify":"activity","activity_sku_prices":[{"id":6,"activity_id":1,"goods_sku_price_id":45,"goods_id":17,"stock":0,"price":"0.00","ext":null,"status":"down","createtime":"2025-03-09 15:19:56","updatetime":"2025-03-09 15:19:56","status_text":"下架","sales":false},{"id":8,"activity_id":1,"goods_sku_price_id":47,"goods_id":17,"stock":1000,"price":"218.00","ext":null,"status":"up","createtime":"2025-03-09 15:19:56","updatetime":"2025-03-09 15:19:56","status_text":"上架","sales":false},{"id":7,"activity_id":1,"goods_sku_price_id":46,"goods_id":17,"stock":0,"price":"0.00","ext":null,"status":"down","createtime":"2025-03-09 15:19:56","updatetime":"2025-03-09 15:19:56","status_text":"下架","sales":false}],"end_time":"1809100799","start_time":"1741536000","richtext_id":"0","status":"ing","status_text":"进行中"}]
     * promos : [{"id":"2","title":"惊喜大促","type":"full_reduce","type_text":"满减","classify":"promo","goods_ids":"","richtext_id":"0","richtext_title":"","prehead_time":"1741449600","start_time":"1741449600","end_time":"1809100799","rules":{"type":"money","discounts":[{"full":"300","discount":"50"}]},"status":"ing","status_text":"进行中","tag":"满300元减50","tags":["满300元减50"],"texts":["满300元减50元"]}]
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
    private List<ActivitiesBean> activities;
    private List<PromosBean> promos;

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

    public List<ActivitiesBean> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivitiesBean> activities) {
        this.activities = activities;
    }

    public List<PromosBean> getPromos() {
        return promos;
    }

    public void setPromos(List<PromosBean> promos) {
        this.promos = promos;
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
         * title : 频响范围
         * content : 20Hz-20KHz
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
         * id : 45
         * goods_sku_ids : 42
         * goods_sku_text : ["白色"]
         * goods_id : 17
         * image :
         * stock : 1019
         * stock_warning : null
         * sales : 1
         * sn :
         * weight : 0.00
         * cost_price : 120.00
         * original_price : 389.00
         * price : 299.00
         * status : up
         * weigh : 0
         * createtime : 2022-10-13 15:59:25
         * updatetime : 2025-07-14 15:33:33
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

    public static class ActivitiesBean {
        /**
         * title : 惊喜秒杀
         * type_text : 秒杀
         * prehead_time : 1741504692
         * richtext_title :
         * rules : {"is_commission":"1","is_free_shipping":"1","sales_show_type":"real","limit_num":"1","order_auto_close":"5"}
         * type : seckill
         * id : 1
         * goods_ids : 21,18,17
         * classify : activity
         * activity_sku_prices : [{"id":6,"activity_id":1,"goods_sku_price_id":45,"goods_id":17,"stock":0,"price":"0.00","ext":null,"status":"down","createtime":"2025-03-09 15:19:56","updatetime":"2025-03-09 15:19:56","status_text":"下架","sales":false},{"id":8,"activity_id":1,"goods_sku_price_id":47,"goods_id":17,"stock":1000,"price":"218.00","ext":null,"status":"up","createtime":"2025-03-09 15:19:56","updatetime":"2025-03-09 15:19:56","status_text":"上架","sales":false},{"id":7,"activity_id":1,"goods_sku_price_id":46,"goods_id":17,"stock":0,"price":"0.00","ext":null,"status":"down","createtime":"2025-03-09 15:19:56","updatetime":"2025-03-09 15:19:56","status_text":"下架","sales":false}]
         * end_time : 1809100799
         * start_time : 1741536000
         * richtext_id : 0
         * status : ing
         * status_text : 进行中
         */

        private String title;
        private String type_text;
        private String prehead_time;
        private String richtext_title;
        private RulesBean rules;
        private String type;
        private String id;
        private String goods_ids;
        private String classify;
        private String end_time;
        private String start_time;
        private String richtext_id;
        private String status;
        private String status_text;
        private List<ActivitySkuPricesBean> activity_sku_prices;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }

        public String getPrehead_time() {
            return prehead_time;
        }

        public void setPrehead_time(String prehead_time) {
            this.prehead_time = prehead_time;
        }

        public String getRichtext_title() {
            return richtext_title;
        }

        public void setRichtext_title(String richtext_title) {
            this.richtext_title = richtext_title;
        }

        public RulesBean getRules() {
            return rules;
        }

        public void setRules(RulesBean rules) {
            this.rules = rules;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_ids() {
            return goods_ids;
        }

        public void setGoods_ids(String goods_ids) {
            this.goods_ids = goods_ids;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getRichtext_id() {
            return richtext_id;
        }

        public void setRichtext_id(String richtext_id) {
            this.richtext_id = richtext_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public List<ActivitySkuPricesBean> getActivity_sku_prices() {
            return activity_sku_prices;
        }

        public void setActivity_sku_prices(List<ActivitySkuPricesBean> activity_sku_prices) {
            this.activity_sku_prices = activity_sku_prices;
        }

        public static class RulesBean {
            /**
             * is_commission : 1
             * is_free_shipping : 1
             * sales_show_type : real
             * limit_num : 1
             * order_auto_close : 5
             */

            private String is_commission;
            private String is_free_shipping;
            private String sales_show_type;
            private String limit_num;
            private String order_auto_close;

            public String getIs_commission() {
                return is_commission;
            }

            public void setIs_commission(String is_commission) {
                this.is_commission = is_commission;
            }

            public String getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(String is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getSales_show_type() {
                return sales_show_type;
            }

            public void setSales_show_type(String sales_show_type) {
                this.sales_show_type = sales_show_type;
            }

            public String getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(String limit_num) {
                this.limit_num = limit_num;
            }

            public String getOrder_auto_close() {
                return order_auto_close;
            }

            public void setOrder_auto_close(String order_auto_close) {
                this.order_auto_close = order_auto_close;
            }
        }

        public static class ActivitySkuPricesBean {
            /**
             * id : 6
             * activity_id : 1
             * goods_sku_price_id : 45
             * goods_id : 17
             * stock : 0
             * price : 0.00
             * ext : null
             * status : down
             * createtime : 2025-03-09 15:19:56
             * updatetime : 2025-03-09 15:19:56
             * status_text : 下架
             * sales : false
             */

            private int id;
            private int activity_id;
            private int goods_sku_price_id;
            private int goods_id;
            private int stock;
            private String price;
            private Object ext;
            private String status;
            private String createtime;
            private String updatetime;
            private String status_text;
            private boolean sales;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public int getGoods_sku_price_id() {
                return goods_sku_price_id;
            }

            public void setGoods_sku_price_id(int goods_sku_price_id) {
                this.goods_sku_price_id = goods_sku_price_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public Object getExt() {
                return ext;
            }

            public void setExt(Object ext) {
                this.ext = ext;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public boolean isSales() {
                return sales;
            }

            public void setSales(boolean sales) {
                this.sales = sales;
            }
        }
    }

    public static class PromosBean {
        /**
         * id : 2
         * title : 惊喜大促
         * type : full_reduce
         * type_text : 满减
         * classify : promo
         * goods_ids :
         * richtext_id : 0
         * richtext_title :
         * prehead_time : 1741449600
         * start_time : 1741449600
         * end_time : 1809100799
         * rules : {"type":"money","discounts":[{"full":"300","discount":"50"}]}
         * status : ing
         * status_text : 进行中
         * tag : 满300元减50
         * tags : ["满300元减50"]
         * texts : ["满300元减50元"]
         */

        private String id;
        private String title;
        private String type;
        private String type_text;
        private String classify;
        private String goods_ids;
        private String richtext_id;
        private String richtext_title;
        private String prehead_time;
        private String start_time;
        private String end_time;
        private RulesBeanX rules;
        private String status;
        private String status_text;
        private String tag;
        private List<String> tags;
        private List<String> texts;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_text() {
            return type_text;
        }

        public void setType_text(String type_text) {
            this.type_text = type_text;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

        public String getGoods_ids() {
            return goods_ids;
        }

        public void setGoods_ids(String goods_ids) {
            this.goods_ids = goods_ids;
        }

        public String getRichtext_id() {
            return richtext_id;
        }

        public void setRichtext_id(String richtext_id) {
            this.richtext_id = richtext_id;
        }

        public String getRichtext_title() {
            return richtext_title;
        }

        public void setRichtext_title(String richtext_title) {
            this.richtext_title = richtext_title;
        }

        public String getPrehead_time() {
            return prehead_time;
        }

        public void setPrehead_time(String prehead_time) {
            this.prehead_time = prehead_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public RulesBeanX getRules() {
            return rules;
        }

        public void setRules(RulesBeanX rules) {
            this.rules = rules;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTexts() {
            return texts;
        }

        public void setTexts(List<String> texts) {
            this.texts = texts;
        }

        public static class RulesBeanX {
            /**
             * type : money
             * discounts : [{"full":"300","discount":"50"}]
             */

            private String type;
            private List<DiscountsBean> discounts;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<DiscountsBean> getDiscounts() {
                return discounts;
            }

            public void setDiscounts(List<DiscountsBean> discounts) {
                this.discounts = discounts;
            }

            public static class DiscountsBean {
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
}
