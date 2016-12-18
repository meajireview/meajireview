package com.meajireview.meajireview_android.item;

/**
 * Created by songm on 2016-12-19.
 */

public class ReviewHeader {
    boolean is_recommend;
    String content, odjectId;
    int shopId;

    public int getShopId() {
        return shopId;
    }

    public boolean getRating() {
        return is_recommend;
    }

    public String getContent() {
        return content;
    }

    public String getOdjectId() {
        return odjectId;
    }

    public ReviewHeader(int shopId, boolean is_recommend, String contents, String objectId) {
        this.shopId = shopId;
        this.is_recommend = is_recommend;
        this.content = contents;
        this.odjectId = objectId;
    }
}
