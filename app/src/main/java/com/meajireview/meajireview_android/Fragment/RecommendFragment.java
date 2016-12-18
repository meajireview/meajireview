package com.meajireview.meajireview_android.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.meajireview.meajireview_android.R;

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
        TextView shopName = (TextView)container.findViewById(R.id.shopName);

        Bundle extra = getArguments();
        String id = extra.getString("category");

        shopName.setText(id);
        return container;
    }
}
