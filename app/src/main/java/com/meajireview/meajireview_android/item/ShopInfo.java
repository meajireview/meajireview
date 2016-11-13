package com.meajireview.meajireview_android.item;

/**
 * Created by Test on 2016. 11. 7..
 */

public class ShopInfo {
    int shopId;
    String shopName;
    String shopCall;
    String shopRating;

    public int getShopId() {
        return shopId;
    }

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

    public ShopInfo(int shopId, String shopName, String shopCall, String shopRating)
    {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopCall = shopCall;
        this.shopRating = shopRating;
    }
}
