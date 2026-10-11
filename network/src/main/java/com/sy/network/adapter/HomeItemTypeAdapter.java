package com.sy.network.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sy.network.bean.ResHomeItem;

import java.io.IOException;

public class HomeItemTypeAdapter extends TypeAdapter<ResHomeItem> {
        private final Gson gson = new Gson();

        @Override
        public void write(JsonWriter out, ResHomeItem value) throws IOException {
            // 序列化逻辑（如果需要将对象写回JSON）
            out.beginObject();
            out.name("type").value(value.getType());
            out.name("data");
            gson.toJson(value.getData(), value.getData().getClass(), out);
            out.name("style");
            gson.toJson(value.getStyle(), ResHomeItem.StyleBeanX.class, out);
            out.endObject();
        }

        @Override
        public ResHomeItem read(JsonReader in) throws IOException {
            ResHomeItem item = new ResHomeItem();
            in.beginObject();

            while (in.hasNext()) {
                String fieldName = in.nextName();
                switch (fieldName) {
                    case "type":
                        item.setType(in.nextString());
                        break;
                    case "data":
                        // 根据 type 字段的值来解析 data
                        item.setData(parseDataBasedOnType(in, item.getType()));
                        break;
                    case "style":
                        item.setStyle(gson.fromJson(in, ResHomeItem.StyleBeanX.class));
                        break;
                    default:
                        in.skipValue(); // 跳过未知字段
                        break;
                }
            }

            in.endObject();
            return item;
        }

        private Object parseDataBasedOnType(JsonReader in, String type) throws IOException {
            switch (type) {
                case "searchBlock":
                    return gson.fromJson(in, ResHomeItem.SearchBlockData.class);
                case "imageBanner":
                    return gson.fromJson(in, ResHomeItem.ImageBannerData.class);
                case "menuButton":
                    return gson.fromJson(in, ResHomeItem.MenuButtonData.class);
                case "imageCube":
                    return gson.fromJson(in, ResHomeItem.ImageCubeData.class);
                case "titleBlock":
                    return gson.fromJson(in, ResHomeItem.TitleBlockData.class);
                case "goodsCard":
                    return gson.fromJson(in, ResHomeItem.GoodsCardData.class);
                default:
                    // 对于未知类型，返回原始的 JsonObject
                    JsonObject jsonObject = gson.fromJson(in, JsonObject.class);
                    return jsonObject;
            }
        }
}
