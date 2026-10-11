package com.sy.network.bean;

import java.util.List;

public class ResHomeItem {
    /**
     * type : searchBlock
     * data : {"placeholder":"请输入关键词","borderRadius":27,"keywords":[]}
     * style : {"background":{"type":"color","bgImage":"","bgColor":"#FFFFFF"},"marginLeft":0,"marginRight":0,"marginTop":0,"marginBottom":8,"borderRadiusTop":0,"borderRadiusBottom":0,"padding":8}
     */

    private String type;
    private Object data;
    private StyleBeanX style;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public StyleBeanX getStyle() {
        return style;
    }

    public void setStyle(StyleBeanX style) {
        this.style = style;
    }

    public class StyleBeanX {
        /**
         * background : {"type":"color","bgImage":"","bgColor":"#FFFFFF"}
         * marginLeft : 0
         * marginRight : 0
         * marginTop : 0
         * marginBottom : 8
         * borderRadiusTop : 0
         * borderRadiusBottom : 0
         * padding : 8
         */

        private BackgroundBeanXX background;
        private int marginLeft;
        private int marginRight;
        private int marginTop;
        private int marginBottom;
        private int borderRadiusTop;
        private int borderRadiusBottom;
        private int padding;

        public BackgroundBeanXX getBackground() {
            return background;
        }

        public void setBackground(BackgroundBeanXX background) {
            this.background = background;
        }

        public int getMarginLeft() {
            return marginLeft;
        }

        public void setMarginLeft(int marginLeft) {
            this.marginLeft = marginLeft;
        }

        public int getMarginRight() {
            return marginRight;
        }

        public void setMarginRight(int marginRight) {
            this.marginRight = marginRight;
        }

        public int getMarginTop() {
            return marginTop;
        }

        public void setMarginTop(int marginTop) {
            this.marginTop = marginTop;
        }

        public int getMarginBottom() {
            return marginBottom;
        }

        public void setMarginBottom(int marginBottom) {
            this.marginBottom = marginBottom;
        }

        public int getBorderRadiusTop() {
            return borderRadiusTop;
        }

        public void setBorderRadiusTop(int borderRadiusTop) {
            this.borderRadiusTop = borderRadiusTop;
        }

        public int getBorderRadiusBottom() {
            return borderRadiusBottom;
        }

        public void setBorderRadiusBottom(int borderRadiusBottom) {
            this.borderRadiusBottom = borderRadiusBottom;
        }

        public int getPadding() {
            return padding;
        }

        public void setPadding(int padding) {
            this.padding = padding;
        }

        public class BackgroundBeanXX {
            /**
             * type : color
             * bgImage :
             * bgColor : #FFFFFF
             */

            private String type;
            private String bgImage;
            private String bgColor;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBgImage() {
                return bgImage;
            }

            public void setBgImage(String bgImage) {
                this.bgImage = bgImage;
            }

            public String getBgColor() {
                return bgColor;
            }

            public void setBgColor(String bgColor) {
                this.bgColor = bgColor;
            }
        }
    }


    /**
     * 根据type去解析不同类型的data
     */
    // searchBlock 类型
    public class SearchBlockData {
        private String placeholder;
        private int borderRadius;
        private List<String> keywords;

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public int getBorderRadius() {
            return borderRadius;
        }

        public void setBorderRadius(int borderRadius) {
            this.borderRadius = borderRadius;
        }

        public List<String> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<String> keywords) {
            this.keywords = keywords;
        }
    }

    // imageBanner 类型
    public class ImageBannerData {
        private int mode;
        private int indicator;
        private List<BannerItem> list;
        private int space;

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public int getIndicator() {
            return indicator;
        }

        public void setIndicator(int indicator) {
            this.indicator = indicator;
        }

        public List<BannerItem> getList() {
            return list;
        }

        public void setList(List<BannerItem> list) {
            this.list = list;
        }

        public int getSpace() {
            return space;
        }

        public void setSpace(int space) {
            this.space = space;
        }
    }

    public class BannerItem {
        private String title;
        private String type;
        private String src;
        private String poster;
        private String url;

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

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    // menuButton 类型
    public class MenuButtonData {
        private int layout;
        private int col;
        private int row;
        private List<MenuButtonItem> list;

        public int getLayout() {
            return layout;
        }

        public void setLayout(int layout) {
            this.layout = layout;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public List<MenuButtonItem> getList() {
            return list;
        }

        public void setList(List<MenuButtonItem> list) {
            this.list = list;
        }
    }

    public class MenuButtonItem {
        private String src;
        private Title title;
        private String url;
        private Badge badge;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Badge getBadge() {
            return badge;
        }

        public void setBadge(Badge badge) {
            this.badge = badge;
        }
    }

    public class Title {
        private String text;
        private String color;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public class Badge {
        private int show;
        private String text;
        private String color;
        private String bgColor;

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getBgColor() {
            return bgColor;
        }

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }
    }

    //ImageCube 类型
    public class ImageCubeData {
        private int borderRadiusTop;
        private int borderRadiusBottom;
        private int space;
        private List<ImageCubeItem> list;

        // Getter and Setter
        public int getBorderRadiusTop() { return borderRadiusTop; }
        public void setBorderRadiusTop(int borderRadiusTop) { this.borderRadiusTop = borderRadiusTop; }

        public int getBorderRadiusBottom() { return borderRadiusBottom; }
        public void setBorderRadiusBottom(int borderRadiusBottom) { this.borderRadiusBottom = borderRadiusBottom; }

        public int getSpace() { return space; }
        public void setSpace(int space) { this.space = space; }

        public List<ImageCubeItem> getList() { return list; }
        public void setList(List<ImageCubeItem> list) { this.list = list; }
    }
    public class ImageCubeItem {
        private int width;
        private int height;
        private int top;
        private int left;
        private String src;
        private String url;

        // Getter and Setter
        public int getWidth() { return width; }
        public void setWidth(int width) { this.width = width; }

        public int getHeight() { return height; }
        public void setHeight(int height) { this.height = height; }

        public int getTop() { return top; }
        public void setTop(int top) { this.top = top; }

        public int getLeft() { return left; }
        public void setLeft(int left) { this.left = left; }

        public String getSrc() { return src; }
        public void setSrc(String src) { this.src = src; }

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

    //TitleBlock 类型
    public class TitleBlockData {
        private String src;
        private String location;
        private int skew;
        private TitleBlockTitle title;
        private TitleBlockSubtitle subtitle;
        private More more;

        // Getter and Setter
        public String getSrc() { return src; }
        public void setSrc(String src) { this.src = src; }

        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }

        public int getSkew() { return skew; }
        public void setSkew(int skew) { this.skew = skew; }

        public TitleBlockTitle getTitle() { return title; }
        public void setTitle(TitleBlockTitle title) { this.title = title; }

        public TitleBlockSubtitle getSubtitle() { return subtitle; }
        public void setSubtitle(TitleBlockSubtitle subtitle) { this.subtitle = subtitle; }

        public More getMore() { return more; }
        public void setMore(More more) { this.more = more; }
    }

    public class TitleBlockTitle {
        private String text;
        private String color;
        private int textFontSize;
        private List<String> other;

        // Getter and Setter
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }

        public int getTextFontSize() { return textFontSize; }
        public void setTextFontSize(int textFontSize) { this.textFontSize = textFontSize; }

        public List<String> getOther() { return other; }
        public void setOther(List<String> other) { this.other = other; }
    }

    public class TitleBlockSubtitle {
        private String text;
        private String color;
        private int textFontSize;
        private List<String> other;

        // Getter and Setter
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }

        public int getTextFontSize() { return textFontSize; }
        public void setTextFontSize(int textFontSize) { this.textFontSize = textFontSize; }

        public List<String> getOther() { return other; }
        public void setOther(List<String> other) { this.other = other; }
    }

    public class More {
        private int show;
        private String url;

        // Getter and Setter
        public int getShow() { return show; }
        public void setShow(int show) { this.show = show; }

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

    //GoodsCard 类型
    public class GoodsCardData {
        private int mode;
        private GoodsFields goodsFields;
        private BuyNowStyle buyNowStyle;
        private TagStyle tagStyle;
        private List<Integer> goodsIds;
        private int borderRadiusTop;
        private int borderRadiusBottom;
        private int space;

        // Getter and Setter
        public int getMode() { return mode; }
        public void setMode(int mode) { this.mode = mode; }

        public GoodsFields getGoodsFields() { return goodsFields; }
        public void setGoodsFields(GoodsFields goodsFields) { this.goodsFields = goodsFields; }

        public BuyNowStyle getBuyNowStyle() { return buyNowStyle; }
        public void setBuyNowStyle(BuyNowStyle buyNowStyle) { this.buyNowStyle = buyNowStyle; }

        public TagStyle getTagStyle() { return tagStyle; }
        public void setTagStyle(TagStyle tagStyle) { this.tagStyle = tagStyle; }

        public List<Integer> getGoodsIds() { return goodsIds; }
        public void setGoodsIds(List<Integer> goodsIds) { this.goodsIds = goodsIds; }

        public int getBorderRadiusTop() { return borderRadiusTop; }
        public void setBorderRadiusTop(int borderRadiusTop) { this.borderRadiusTop = borderRadiusTop; }

        public int getBorderRadiusBottom() { return borderRadiusBottom; }
        public void setBorderRadiusBottom(int borderRadiusBottom) { this.borderRadiusBottom = borderRadiusBottom; }

        public int getSpace() { return space; }
        public void setSpace(int space) { this.space = space; }
    }

    public class GoodsFields {
        private Field title;
        private Field subtitle;
        private Field price;
        private Field original_price;
        private Field sales;
        private Field stock;

        // Getter and Setter
        public Field getTitle() { return title; }
        public void setTitle(Field title) { this.title = title; }

        public Field getSubtitle() { return subtitle; }
        public void setSubtitle(Field subtitle) { this.subtitle = subtitle; }

        public Field getPrice() { return price; }
        public void setPrice(Field price) { this.price = price; }

        public Field getOriginal_price() { return original_price; }
        public void setOriginal_price(Field original_price) { this.original_price = original_price; }

        public Field getSales() { return sales; }
        public void setSales(Field sales) { this.sales = sales; }

        public Field getStock() { return stock; }
        public void setStock(Field stock) { this.stock = stock; }
    }

    public class Field {
        private int show;
        private String color;

        // Getter and Setter
        public int getShow() { return show; }
        public void setShow(int show) { this.show = show; }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
    }

    public class BuyNowStyle {
        private int mode;
        private String text;
        private String color1;
        private String color2;
        private String src;

        // Getter and Setter
        public int getMode() { return mode; }
        public void setMode(int mode) { this.mode = mode; }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public String getColor1() { return color1; }
        public void setColor1(String color1) { this.color1 = color1; }

        public String getColor2() { return color2; }
        public void setColor2(String color2) { this.color2 = color2; }

        public String getSrc() { return src; }
        public void setSrc(String src) { this.src = src; }
    }

    public class TagStyle {
        private int show;
        private String src;

        // Getter and Setter
        public int getShow() { return show; }
        public void setShow(int show) { this.show = show; }

        public String getSrc() { return src; }
        public void setSrc(String src) { this.src = src; }
    }
}
