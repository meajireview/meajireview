package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.activity.ShopListActivity;
import com.meajireview.meajireview_android.item.CategoryItem;

import java.util.ArrayList;

/**
 * CategoryAdapter
 *  카테고리 어뎁터
 * Created by songmho on 2016-10-04.
 */
public class CategoryAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<CategoryItem> items;

    public CategoryAdapter(Context context, ArrayList<CategoryItem> items) {
        this.context=context;
        this.items=items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CategoryItem item = items.get(position);
        ((ViewHolder)holder).txtCategory.setText(item.getCategory());

        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/BMJUA_ttf.ttf");
        ((ViewHolder)holder).txtCategory.setTypeface(typeFace);

        ((ViewHolder)holder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(), ShopListActivity.class);
                intent.putExtra("category",""+item.getCategory());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * 카테고리 리스트의 item 관련 class
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCategory;
        FrameLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (FrameLayout)itemView.findViewById(R.id.container);
            txtCategory = (TextView)itemView.findViewById(R.id.txtCategory);
        }
    }
}
