package com.meajireview.meajireview_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.CategoryAdapter;
import com.meajireview.meajireview_android.item.CategoryItem;

import java.util.ArrayList;

/**
 * Created by songmho on 2016-10-09.
 */

public class ListFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        RecyclerView recyclerView =(RecyclerView)view.findViewById(R.id.recyclerView);

        ArrayList<CategoryItem> items= new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        items.add(new CategoryItem("즐겨찾기"));
        items.add(new CategoryItem("랜덤"));
        items.add(new CategoryItem("밥"));
        items.add(new CategoryItem("중식"));
        items.add(new CategoryItem("양식"));
        items.add(new CategoryItem("치킨"));
        items.add(new CategoryItem("야식"));
        items.add(new CategoryItem("카페"));
        items.add(new CategoryItem("술집"));

        recyclerView.setAdapter(new CategoryAdapter(getActivity().getApplicationContext(),items));
        return view;
    }
}
