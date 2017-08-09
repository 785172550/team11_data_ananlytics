package com.example.controller;

import com.example.dao.DemoRepository;
import com.example.dao.NasdaqRespostory;
import com.example.model.Stock;
import com.example.utils.DemoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */

@RestController
public class StockController {

    @Autowired
    private DemoRepository respostory;


//    @RequestMapping("/stock/{name}")
//    public Object getStockDayInfo(@PathVariable String name) {
//        respostory.getStockByDateNoTime(201)
//        return null;
//    }

    @RequestMapping("/stock_10")
    public List<Stock> getTop10() {
        List<Stock> res = new ArrayList<>();
        res = DemoUtils.GenerateStock(10);
        return res;
    }

    @RequestMapping("/stock_special")
    public List<Stock> getSpecial() {
        List<Stock> res = new ArrayList<>();
        res = DemoUtils.GenerateStock(9);
        return res;
    }
}
