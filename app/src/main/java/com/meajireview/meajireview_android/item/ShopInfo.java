package com.meajireview.meajireview_android.item;

/**
 * Created by Test on 2016. 11. 7..
 */

public class ShopInfo {

    String shopName;
    String shopCall;
    String shopRating;

    public String getShopName()
    {
        return shopName;
    }

    public String getShopCall()
    {
        return shopCall;
    }

    public String getShopRating()
    {
        return shopRating;
    }

    public ShopInfo(String shopName, String shopCall, String shopRating)
    {
        this.shopName = shopName;
        this.shopCall = shopCall;
        this.shopRating = shopRating;
    }
}
