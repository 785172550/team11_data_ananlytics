package com.example.utils;

import com.example.model.Stock;

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
}
