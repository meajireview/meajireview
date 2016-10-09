package com.meajireview.meajireview_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.meajireview.meajireview_android.fragment.ListFragment;

import java.util.ArrayList;

/**
 * Created by songmho on 2016-10-09.
 */
public class ShopViewPagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> mFragments = new ArrayList<>();
    ArrayList<String> mTitles = new ArrayList<>();
    FragmentManager fragmentManager;
    public ShopViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager=fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(ListFragment listFragment, String title) {
        mFragments.add(listFragment);
        mTitles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
