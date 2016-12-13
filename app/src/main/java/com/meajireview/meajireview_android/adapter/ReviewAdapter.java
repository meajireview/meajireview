package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.activity.ReviewActivity;
import com.meajireview.meajireview_android.item.ReviewItem;
import com.meajireview.meajireview_android.item.ShopHeader;
import com.meajireview.meajireview_android.item.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songm on 2016-11-24.
 */
public class ReviewAdapter extends RecyclerView.Adapter {

    Context context;
    ReviewItem reviewHeader;
    ArrayList<ReviewItem> reviewItems;

    static final int HEADER = 0;
    static final int BODY = 1;
    public ReviewAdapter(Context context, ReviewItem reviewHeader, ArrayList<ReviewItem> reviewItems) {
        this.context = context;
        this.reviewHeader = reviewHeader;
        this.reviewItems = reviewItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=null;
        if(viewType==HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_review,parent,false);
            return new ReviewAdapter.ViewHeader(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,parent,false);
            return new ReviewAdapter.ViewHolder(v);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(position == HEADER)
            return HEADER;
        else
            return BODY;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ReviewAdapter.ViewHeader){
            ((ViewHeader)holder).ratingBar.setRating((float)3.5);
            ((ViewHeader)holder).editContent.setHint("리뷰를 적어주세요!");
        }else{
            ((ViewHolder)holder).txtRating.setText("3.0");
            ((ViewHolder)holder).txtReview.setText("테스트 중입니다...");
        }
    }

    @Override
    public int getItemCount() {
        return reviewItems.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRating;
        TextView txtReview;
        public ViewHolder(View v) {
            super(v);
            txtRating = (TextView)itemView.findViewById(R.id.txtRating);
            txtReview = (TextView)itemView.findViewById(R.id.txtReview);
        }
    }

    public class ViewHeader extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        EditText editContent;
        public ViewHeader(View v) {
            super(v);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
            editContent = (EditText)itemView.findViewById(R.id.editContent);
        }
    }
}
