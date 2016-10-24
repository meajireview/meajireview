package com.meajireview.meajireview_android.item;

/**
 * Created by songmho on 2016-10-24.
 */

public class ShopHeader {
    String open,delivery,rating;

    public String getOpen() {
        return open;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getRating() {
        return rating;
    }
    public ShopHeader(String open,String delivery, String rating){
        this.open=open;
        this.delivery=delivery;
        this.rating=rating;
    }
}
