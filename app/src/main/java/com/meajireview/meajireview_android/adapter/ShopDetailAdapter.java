package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.item.ShopHeader;
import com.meajireview.meajireview_android.item.ShopItem;

import java.util.List;

/**
 * Created by songmho on 2016-10-24.
 */
public class ShopDetailAdapter extends RecyclerView.Adapter {
    Context context;
    ShopHeader shopHeader;
    List<ShopItem> shopItems;

    static final int HEADER = 0;
    static final int BODY = 1;

    public ShopDetailAdapter(Context context, ShopHeader shopHeader, List<ShopItem> shopItems) {
        this.context = context;
        this.shopHeader = shopHeader;
        this.shopItems = shopItems;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == HEADER)
            return HEADER;
        else
            return BODY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=null;
        if(viewType==HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_shop,parent,false);
            return new ViewHeader(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHeader){
            ((ViewHeader)holder).txtOpen.setText(shopHeader.getOpen());
            ((ViewHeader)holder).txtDelivery.setText(shopHeader.getDelivery());
            ((ViewHeader)holder).txtRating.setText(shopHeader.getRating());
            ((ViewHeader)holder).txtPhoneNum.setText(shopHeader.getPhoneNum());
            ((ViewHeader)holder).txtMyReview.setText(Html.fromHtml("<u>" + "리뷰를 남겨주세요." + "</u>"));

        }else{
            ((ViewHolder)holder).txtFood.setText(shopItems.get(position-1).getFood());
            ((ViewHolder)holder).txtPrice.setText(shopItems.get(position-1).getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return shopItems.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtFood, txtPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            txtFood = (TextView)itemView.findViewById(R.id.txtFood);
            txtPrice = (TextView)itemView.findViewById(R.id.txtPrice);
        }
    }

    public class ViewHeader extends RecyclerView.ViewHolder{
        TextView txtOpen, txtDelivery, txtRating, txtPhoneNum, txtMyReview;
        public ViewHeader(View itemView) {
            super(itemView);
            txtOpen = (TextView)itemView.findViewById(R.id.txtOpen);
            txtDelivery = (TextView)itemView.findViewById(R.id.txtDelivery);
            txtRating = (TextView)itemView.findViewById(R.id.txtRating);
            txtPhoneNum = (TextView)itemView.findViewById(R.id.txtPhoneNum);
            txtMyReview = (TextView)itemView.findViewById(R.id.txtMyReview);
        }
    }
}
