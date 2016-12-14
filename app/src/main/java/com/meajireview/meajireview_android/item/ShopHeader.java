package com.meajireview.meajireview_android.item;

/**
 * Created by songmho on 2016-10-24.
 */

public class ShopHeader {
    String open,delivery,rating, phoneNum, myRecommend, myContent;
    int shopId;

    public String getOpen() {
        return open;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getRating() {
        return rating;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getShopId() {
        return shopId;
    }

    public String getMyContent() {
        return myContent;
    }

    public String getMyRecommend() {
        return myRecommend;
    }

    public ShopHeader(int shopId, String open, String delivery, String rating, String phoneNum, String myrecommend, String myContent){
        this.shopId = shopId;
        this.open=open;
        this.delivery=delivery;
        this.rating=rating;
        this.phoneNum=phoneNum;
        this.myContent = myContent;
        this.myRecommend = myrecommend;
    }
}
