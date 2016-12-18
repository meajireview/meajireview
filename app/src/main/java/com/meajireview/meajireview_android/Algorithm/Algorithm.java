package com.meajireview.meajireview_android.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songm on 2016-12-17.
 */

public class Algorithm {
    List<RawData> rawDatas = new ArrayList<>();
    List<Data> datas = new ArrayList<>();
    List<Data> resultDatas =new ArrayList<>();

    public Algorithm(List<RawData> rawDatas){
        this.rawDatas = rawDatas;

        for(RawData d : rawDatas) {
            double a = d.getWholeCount()*getRate();
            datas.add(new Data(d.getContent(),a));
        }

        for(int i = 0; i<rawDatas.size();i++) {
            double a = (0.7*rawDatas.get(i).getCurCount())+(0.3*datas.get(i).getCount());
            resultDatas.add(new Data(rawDatas.get(i).getContent(),a));
        }

        QuickSort quickSort = new QuickSort();
        quickSort.sort(resultDatas,0,resultDatas.size()-1);
    }

    public List<RawData> getRawDatas() {
        return this.rawDatas;
    }

    public List<Data> getResultDatas() {
        return resultDatas;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public double getRate(){
        return RawData.getCurCountSum() / RawData.getWholeCountSum();
    }

}
