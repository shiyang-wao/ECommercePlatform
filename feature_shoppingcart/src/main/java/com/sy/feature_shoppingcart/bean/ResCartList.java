package com.sy.feature_shoppingcart.bean;

import java.util.List;

public class ResCartList {
    /**
     * id : 232
     * goods_id : 14
     * user_id : 18
     * goods_sku_price_id : 38
     * goods_num : 1
     * snapshot_price : 4498.00
     * createtime : 2025-10-01 00:19:28
     * updatetime : 2025-10-01 00:19:28
     * tags : null
     * status : normal
     * goods : {"id":14,"type":"normal","title":"【国家补贴】华为 MateBook 14 酷睿Ultra 笔记本电脑 2.8K OLED 触控手写屏 轻薄灵动机身 AI电脑官方旗舰店","subtitle":"轻薄本护眼全面屏","category_ids":"8,62,63,86,87,88,35","image":"/uploads/20250226/574b97e1b319854931b86173f4accb0d.jpg","image_wh":{"w":"800","h":"800"},"images":["/uploads/20250226/574b97e1b319854931b86173f4accb0d.jpg","/uploads/20250226/bed8ef17da4a92ba30ef48b0c5d01e82.jpg","/uploads/20250226/5652c93ffeb306dacd07524ef2038eca.jpg"],"params":[{"title":"型号","content":"NDIODE WFH9"},{"title":"电池容量","content":"56Wh"},{"title":"处理器","content":"第十一代智能英特尔酷睿I5处理器"},{"title":"显示器","content":"14寸"},{"title":"显卡","content":"集成显卡"}],"original_price":"5380.00","price":["4498.00"],"is_sku":1,"limit_type":"none","limit_num":0,"likes":0,"views":606,"sales":0,"sales_show_type":"exact","stock_show_type":"exact","show_sales":0,"service_ids":[1,2,3,4],"dispatch_type":"express","dispatch_id":1,"is_offline":0,"status":"up","weigh":0,"createtime":"2023-03-09 17:10:33","updatetime":"2025-02-26 14:15:21","deletetime":null,"status_text":"上架中","type_text":"实体商品","dispatch_type_text":"快递物流"}
     * sku_price : {"id":38,"goods_sku_ids":"30,32","goods_sku_text":["MateBook D 14 2022款","i5 16G 512G 皓月银"],"goods_id":14,"image":"","stock":20,"stock_warning":null,"sales":0,"sn":"","weight":"0.00","cost_price":"4000.00","original_price":"5380.00","price":"4498.00","status":"up","weigh":0,"createtime":"2022-10-13 15:59:25","updatetime":"2025-02-26 14:15:21","status_text":"上架"}
     */

    private int id;
    private int goods_id;
    private int user_id;
    private int goods_sku_price_id;
    private int goods_num;
    private String snapshot_price;
    private String createtime;
    private String updatetime;
    private Object tags;
    private String status;
    private GoodsBean goods;
    private SkuPriceBean sku_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGoods_sku_price_id() {
        return goods_sku_price_id;
    }

    public void setGoods_sku_price_id(int goods_sku_price_id) {
        this.goods_sku_price_id = goods_sku_price_id;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public String getSnapshot_price() {
        return snapshot_price;
    }

    public void setSnapshot_price(String snapshot_price) {
        this.snapshot_price = snapshot_price;
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

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public SkuPriceBean getSku_price() {
        return sku_price;
    }

    public void setSku_price(SkuPriceBean sku_price) {
        this.sku_price = sku_price;
    }

    public static class GoodsBean {
        /**
         * id : 14
         * type : normal
         * title : 【国家补贴】华为 MateBook 14 酷睿Ultra 笔记本电脑 2.8K OLED 触控手写屏 轻薄灵动机身 AI电脑官方旗舰店
         * subtitle : 轻薄本护眼全面屏
         * category_ids : 8,62,63,86,87,88,35
         * image : /uploads/20250226/574b97e1b319854931b86173f4accb0d.jpg
         * image_wh : {"w":"800","h":"800"}
         * images : ["/uploads/20250226/574b97e1b319854931b86173f4accb0d.jpg","/uploads/20250226/bed8ef17da4a92ba30ef48b0c5d01e82.jpg","/uploads/20250226/5652c93ffeb306dacd07524ef2038eca.jpg"]
         * params : [{"title":"型号","content":"NDIODE WFH9"},{"title":"电池容量","content":"56Wh"},{"title":"处理器","content":"第十一代智能英特尔酷睿I5处理器"},{"title":"显示器","content":"14寸"},{"title":"显卡","content":"集成显卡"}]
         * original_price : 5380.00
         * price : ["4498.00"]
         * is_sku : 1
         * limit_type : none
         * limit_num : 0
         * likes : 0
         * views : 606
         * sales : 0
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
         * updatetime : 2025-02-26 14:15:21
         * deletetime : null
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
        private String status_text;
        private String type_text;
        private String dispatch_type_text;
        private List<String> images;
        private List<ParamsBean> params;
        private List<String> price;
        private List<Integer> service_ids;

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
             * title : 型号
             * content : NDIODE WFH9
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
    }

    public static class SkuPriceBean {
        /**
         * id : 38
         * goods_sku_ids : 30,32
         * goods_sku_text : ["MateBook D 14 2022款","i5 16G 512G 皓月银"]
         * goods_id : 14
         * image :
         * stock : 20
         * stock_warning : null
         * sales : 0
         * sn :
         * weight : 0.00
         * cost_price : 4000.00
         * original_price : 5380.00
         * price : 4498.00
         * status : up
         * weigh : 0
         * createtime : 2022-10-13 15:59:25
         * updatetime : 2025-02-26 14:15:21
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
