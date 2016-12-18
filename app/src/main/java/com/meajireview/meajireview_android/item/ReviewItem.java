package com.meajireview.meajireview_android.item;

/**
 * Created by songm on 2016-11-24.
 */

public class ReviewItem {
    boolean rating;
    String content, reviewer;
    int shopId;

    public int getShopId() {
        return shopId;
    }


    public boolean getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public String getReviewer() {
        return reviewer;
    }

    public ReviewItem(int shopId, boolean rating, String content, String reviewer){
        this.shopId = shopId;
        this.rating = rating;
        this.content = content;
        this.reviewer = reviewer;
    }
}
