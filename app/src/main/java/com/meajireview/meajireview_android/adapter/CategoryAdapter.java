package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
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
        ((ViewHolder)holder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getCategory(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder class
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCategory;
        CardView container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (CardView)itemView.findViewById(R.id.container);
            txtCategory = (TextView)itemView.findViewById(R.id.txtCategory);
        }
    }
}
