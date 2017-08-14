package com.example.utils;

import com.example.model.Nasdaq_2013;
import com.example.model.Stock;
import com.example.model.abs.Security;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenneth on 2017/8/5.
 */

public class DemoUtils {

    public static List<Stock> GenerateStock(int count) {
        List<Stock> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Stock stock = new Stock();
            stock.setName(count + "--" + System.currentTimeMillis());
            res.add(stock);
        }
        return res;
    }

    public static double get2Double(double f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static <T extends Security>List<List<T>> listUtil(List<T> ls){
        List<List<T>> ll = new ArrayList<>(); //两天的价格
        for (int i = 0; i < ls.size() - 2; i += 2) {
            List<T> item = new ArrayList<>();
            if (ls.get(i).getName().equals(ls.get(i + 1).getName())) {
                item.add(ls.get(i));
                item.add(ls.get(i + 1));
                ll.add(item);
            } else {
                i--;
            }
        }
        return ll;
    }

//    public static <T>List<T> getList()
}
