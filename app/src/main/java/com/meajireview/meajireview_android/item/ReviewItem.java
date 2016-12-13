package com.meajireview.meajireview_android.item;

/**
 * Created by songm on 2016-11-24.
 */

public class ReviewItem {
    int rating;
    String content;

    public int getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }
    public ReviewItem(int rating, String content){
        this.rating = rating;
        this.content = content;

    }
}
