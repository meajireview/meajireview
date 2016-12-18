package com.meajireview.meajireview_android.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.activity.ShopDetailActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by songm on 2016-11-14.
 */
public class RecommendFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parents, Bundle savedInstanceState) {
        final FrameLayout container = (FrameLayout)inflater.inflate(R.layout.fragment_recommnend,parents,false);
        final TextView shopName = (TextView)container.findViewById(R.id.shopName);
        final FrameLayout btChange = (FrameLayout)container.findViewById(R.id.btChagne);
        final FrameLayout btCall = (FrameLayout)container.findViewById(R.id.btCall);

        Bundle extra = getArguments();
        final String id = extra.getString("category");

        Log.e("ddf",id);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
        query.whereEqualTo("category_name",id);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> list, ParseException e) {
                Log.e("ddf",""+list.size());
                if(list.size()>1) {
                    final int pick= ((int) (Math.random() * (list.size()-1) + 1));
                    shopName.setText(list.get(pick).getString("name"));
                    btChange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ShopDetailActivity.class);
                            intent.putExtra("shopId",list.get(pick).getInt("shop_id"));
                            intent.putExtra("shop",list.get(pick).getString("name"));
                            intent.putExtra("open",list.get(pick).getString("open")+"~"+list.get(pick).getString("close"));
                            intent.putExtra("phone",list.get(pick).getInt("phone"));
                            intent.putExtra("rating",list.get(pick).getString(""));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getContext().startActivity(intent);
                        }
                    });
                    btCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",list.get(pick).getString("phone"), null));
                            startActivity(intent);
                        }
                    });
                }
                else {
                    shopName.setText(list.get(0).getString("name"));
                }
            }
        });
        return container;
    }
}
