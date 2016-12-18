package com.meajireview.meajireview_android.Algorithm;

import java.util.List;

/**
 * Created by songm on 2016-12-17.
 */

public class QuickSort {
    public void sort(List<Data> data, int l, int r){
        int left = l;
        int right = r;
        double pivot = (data.get(l).getCount() + data.get(r).getCount())/2;

        do{
            while(data.get(left).getCount() < pivot) left++;
            while(data.get(right).getCount() > pivot) right--;
            if(left <= right){
                Data temp = data.get(left);
                data.set(left,data.get(right));
                data.set(right,temp);
                left++;
                right--;
            }
        }while (left <= right);

        if(l < right) sort(data, l, right);
        if(r > left) sort(data, left, r);
    }
}
