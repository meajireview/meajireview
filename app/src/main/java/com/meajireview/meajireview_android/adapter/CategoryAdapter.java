package com.meajireview.meajireview_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.activity.RecommendActivity;
import com.meajireview.meajireview_android.activity.SetupActivity;
import com.meajireview.meajireview_android.activity.ShopListActivity;
import com.meajireview.meajireview_android.item.CategoryItem;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

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

        switch (item.getCategory()){
            case "밥":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.rice);
                break;
            case "중식":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.chiness);
                break;
            case "양식":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.western);
                break;
            case "치킨":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.chicken);
                break;
            case "야식":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.night);
                break;
            case "카페":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.cafe);
                break;
            case "술집":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.alcohol);
                break;
            case "랜덤":
                ((ViewHolder)holder).imageIcon.setImageResource(R.drawable.random);
                break;
        }

        ((ViewHolder)holder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(item.getCategory().equals("랜덤") || item.getCategory().equals("추천"))
                    intent=new Intent(context.getApplicationContext(), RecommendActivity.class);
                else if(item.getCategory().equals("설정"))
                    intent = new Intent(context.getApplicationContext(), SetupActivity.class);
                else
                    intent=new Intent(context.getApplicationContext(), ShopListActivity.class);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
                query.whereEqualTo("name",item.getCategory());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        if(parseObject!=null){
                        parseObject.increment("count",1);
                        parseObject.saveInBackground();}
                    }
                });
                if(ParseUser.getCurrentUser()!=null){
                    switch (item.getCategory()){
                        case "밥":
                            ParseUser.getCurrentUser().increment("rice",1);
                            break;
                        case "중식":
                            ParseUser.getCurrentUser().increment("chiness",1);
                            break;
                        case "양식":
                            ParseUser.getCurrentUser().increment("western",1);
                            break;
                        case "치킨":
                            ParseUser.getCurrentUser().increment("chicken",1);
                            break;
                        case "야식":
                            ParseUser.getCurrentUser().increment("night",1);
                            break;
                        case "카페":
                            ParseUser.getCurrentUser().increment("cafe",1);
                            break;
                        case "술집":
                            ParseUser.getCurrentUser().increment("alcohole",1);
                            break;
                    }
                    ParseUser.getCurrentUser().saveInBackground();
                }

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
        ImageView imageIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            imageIcon = (ImageView)itemView.findViewById(R.id.icon);
            container = (FrameLayout)itemView.findViewById(R.id.container);
            txtCategory = (TextView)itemView.findViewById(R.id.txtCategory);
        }
    }
}
