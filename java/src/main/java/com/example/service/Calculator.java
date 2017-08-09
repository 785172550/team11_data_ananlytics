package com.example.service;

import com.example.model.*;
import java.util.List;
import  java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;

/**
 * Created by Kenneth on 2017/8/9.
 */
public class Calculator {
    private static Calculator calculator = null;

    private Calculator(){

    }

    public static Calculator getCalculator(){
        if(calculator == null) {
            calculator = new Calculator();
        }
        return calculator;
    }

    public List<StocksSort> CalculateIncre(List<List<Stock>> ls){
        List<StocksSort> res = new ArrayList<StocksSort>();

        ls.forEach(item ->
                res.add(new StocksSort(CalculateUpValue(item),item.get(0))));
        Collections.sort(res);

        return res;
    }

    private double CalculateUpValue(List<Stock> tmp){
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
}
