package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.activity.MainActivity;
import com.meajireview.meajireview_android.activity.ShopDetailActivity;
import com.meajireview.meajireview_android.activity.ShopListActivity;
import com.meajireview.meajireview_android.activity.SplashActivity;
import com.meajireview.meajireview_android.item.ShopInfo;

import java.util.ArrayList;

/**
 * Created by Sujin on 2016. 11. 7..
 */

public class ShopListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ShopInfo> shopInfos;
    Boolean isFavorite = false;

    public ShopListAdapter (Context context, ArrayList<ShopInfo> shopInfos)
    {
        this.context = context;
        this.shopInfos = shopInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopinfo, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        final ShopInfo info = shopInfos.get(position);
        ((ViewHolder)holder).shopName.setText(info.getShopName());
        ((ViewHolder)holder).shopCall.setText(info.getShopCall());
        ((ViewHolder)holder).shopRating.setText(info.getShopRating());


        ((ViewHolder)holder).container.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                Intent intent = new Intent(context.getApplicationContext(), ShopDetailActivity.class);
                intent.putExtra("shopId",info.getShopId());
                intent.putExtra("shop",info.getShopName());
                intent.putExtra("open",info.getShopOpen());
                intent.putExtra("phone",info.getShopCall());
                intent.putExtra("rating",info.getShopRating());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView shopName, shopCall, shopRating;
        FrameLayout container;

        public ViewHolder(View infoView) {
            super(infoView);
            shopName = (TextView) infoView.findViewById(R.id.shopName);
            shopCall = (TextView) infoView.findViewById(R.id.shopCall);
            shopRating = (TextView) infoView.findViewById(R.id.shopRating);
            container = (FrameLayout) infoView.findViewById(R.id.container);
        }
    }
}
