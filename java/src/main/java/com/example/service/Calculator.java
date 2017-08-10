package com.example.service;

import com.example.model.*;
import com.example.model.abs.Security;

import java.util.List;
import  java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Kenneth on 2017/8/9.
 *
 */
public class Calculator<T extends Security> {
    private static Calculator calculator = null;

    public Calculator(){

    }

//    public static Calculator getCalculator(){
//        if(calculator == null) {
//            calculator = new Calculator();
//        }
//        return calculator;
//    }

    public List<StocksSort<T>> CalculateIncre(List<List<T>> ls){
        List<StocksSort<T>> res = new ArrayList<>();

        ls.forEach(item ->
                res.add(new StocksSort(CalculateUpValue(item),item.get(0))));
        Collections.sort(res);

        return res;
    }

    private double CalculateUpValue(List<T> tmp){
        return (tmp.get(0).getClose() - tmp.get(1).getClose())/tmp.get(1).getClose();
    }

    public List<StocksSort> CalculateCorr(List<List<Stock>> ls){
        List<Double> mylist = ls.get(0).stream().map(tmp -> tmp.getClose()).collect(Collectors.toList());

        List<StocksSort> res = ls.stream()
                .map(item ->
                        new StocksSort(
                                ClaculateVAR(mylist, item.stream().map(x -> x.getClose())
                                        .collect(Collectors.toList())), item.get(0)))
                .collect(Collectors.toList());

        Collections.sort(res);
        return res;

    }

    private double ClaculateVAR(List<Double> ls1, List<Double> ls2){
        if(ls1.size() == ls2.size()){
            int len = ls1.size();
            double avg1 = ls1.stream().mapToDouble(x -> x).summaryStatistics().getAverage();
            double avg2 = ls2.stream().mapToDouble(x -> x).summaryStatistics().getAverage();
            double cox=0.0;
            double var1 = ls1.stream().mapToDouble(x -> Math.pow((x - avg1), 2)).summaryStatistics().getSum();
            double var2 = ls2.stream().mapToDouble(x -> Math.pow((x - avg2), 2)).summaryStatistics().getSum();
            for(int i=0; i<len; i++){
                cox += (ls1.get(i) - avg1)*(ls2.get(i) - avg2);
            }

            return cox/Math.sqrt(var1*var2);
        }else return 0.0;
    }

    public List<T> Day2longtem(List<T> ls, int N){
        List<T> res = new ArrayList<T>();
        int flag=0;
        double high=0,low=0;
        for(T item : ls){
            flag++;
            if(flag == 1){
                res.add(item);
                high = item.getHigh();
                low = item.getLow();
            }else if(flag == N){
                flag = 0;
                res.get(res.size() - 1).setClose(item.getClose());
                res.get(res.size() - 1).setHigh(high);
                res.get(res.size() - 1).setLow(low);
                high=0;
                low=0;
            }else{
                high=item.getHigh() > high ? item.getHigh() : high;
                low=item.getLow() < low ? item.getLow() : low;
            }

        }
        return res;
    }
}
