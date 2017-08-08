package com.example.controller;

import com.example.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */

@RestController
public class StockController {

    @RequestMapping("/stock/{name}")
    public Object getStockDayInfo(@PathVariable String name) {
        Stock stock = new Stock();
        stock.setName(name);
        stock.setDate(2);
        stock.setId(1);
        return stock;
    }

}
