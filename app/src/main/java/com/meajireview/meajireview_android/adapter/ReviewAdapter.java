package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.activity.ReviewActivity;
import com.meajireview.meajireview_android.item.ReviewItem;
import com.meajireview.meajireview_android.item.ShopHeader;
import com.meajireview.meajireview_android.item.ShopItem;
import com.parse.ParseObject;
import com.parse.ParseUser;

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ReviewAdapter.ViewHeader){
            if(reviewHeader.getContent().length()==0 || reviewHeader.getContent()==null)
                ((ViewHeader)holder).editContent.setHint("리뷰를 적어주세요!");
            else
                ((ViewHeader)holder).editContent.setText(reviewHeader.getContent());
            if(reviewHeader.getRating()){
                ((ViewHeader)holder).btRecommend.setChecked(true);
            }else {
                ((ViewHeader)holder).btUnRecommend.setChecked(true);
            }

            ((ViewHeader)holder).btInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((ViewHeader)holder).btRecommend.isChecked() && ((ViewHeader)holder).btUnRecommend.isChecked()){
                        Toast.makeText(context, "추천/비추천을 해주세요.", Toast.LENGTH_SHORT).show();
                    }else {

                        if(((ViewHeader)holder).btRecommend.isChecked()){
                            ParseObject object = null;
                            object.put("reviewer", ParseUser.getCurrentUser().getUsername());
                            object.put("shop_id", 1);
                            object.put("contents", ((ViewHeader)holder).editContent.getText().toString());
                            object.put("is_recommend",true);
                            object.saveInBackground();
                        }else {
                            ParseObject object = null;
                            object.put("reviewer", ParseUser.getCurrentUser().getUsername());
                            object.put("shop_id", 1);
                            object.put("contents", ((ViewHeader)holder).editContent.getText().toString());
                            object.put("is_recommend",false);
                            object.saveInBackground();
                        }
                    }
                }
            });
        }else{
            ReviewItem curReviewItem = reviewItems.get(position-1);
            if(curReviewItem.getRating()){
                ((ViewHolder)holder).txtRating.setText("추천");
            }else {
                ((ViewHolder)holder).txtRating.setText("비추천");
            }
            ((ViewHolder)holder).txtReview.setText(curReviewItem.getContent());
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
        EditText editContent;
        RadioButton btRecommend, btUnRecommend;
        RadioGroup rdGroup;
        Button btInsert;
        public ViewHeader(View v) {
            super(v);
            editContent = (EditText)itemView.findViewById(R.id.editContent);
            btInsert = (Button)itemView.findViewById(R.id.btInsert);
            btRecommend = (RadioButton)itemView.findViewById(R.id.btRecommend);
            btUnRecommend = (RadioButton)itemView.findViewById(R.id.btUnRecommend);
            rdGroup = (RadioGroup)itemView.findViewById(R.id.rdGroup);
        }
    }
}
