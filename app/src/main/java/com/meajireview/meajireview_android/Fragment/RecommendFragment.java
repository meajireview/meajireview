package com.meajireview.meajireview_android.Fragment;

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
        FrameLayout container = (FrameLayout)inflater.inflate(R.layout.fragment_recommnend,parents,false);
        final TextView shopName = (TextView)container.findViewById(R.id.shopName);

        Bundle extra = getArguments();
        final String id = extra.getString("category");

        Log.e("ddf",id);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
        query.whereEqualTo("category_name",id);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                Log.e("ddf",""+list.size());
                if(list.size()!=1)
                    shopName.setText(list.get(((int)(Math.random()*list.size())+1)).getString("name"));
                else
                    shopName.setText(list.get(0).getString("name"));
            }
        });
        return container;
    }
}
