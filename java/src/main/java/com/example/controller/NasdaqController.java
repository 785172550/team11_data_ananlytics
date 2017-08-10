package com.example.controller;

import com.example.dao.ForexRepository;
import com.example.dao.LiffeRepository;
import com.example.dao.NasdaqRespostory;
import com.example.model.*;
import com.example.service.Calculator;
import com.example.utils.DemoUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kenneth on 2017/8/9.
 */

@RestController
public class NasdaqController {

    Logger log = LoggerFactory.getLogger(NasdaqController.class);
    private String name;
    @Autowired NasdaqRespostory N_repostory;//stock

    @Autowired LiffeRepository L_repository;//future

    @Autowired ForexRepository F_repository;//foreX

    @RequestMapping("/index_msg")// 每天的涨幅最多
    public List<Nasdaq_2013> getIndexMsg() {

        List<Nasdaq_2013> res = N_repostory.getOrderName(20130101, 20130102);
        List<List<Nasdaq_2013>> ll = new ArrayList<>(); //两天的价格

        for (int i = 0; i < res.size() - 2; i += 2) {
            List<Nasdaq_2013> item = new ArrayList<>();
            item.add(res.get(i));
            item.add(res.get(i + 1));
            ll.add(item);
        }
        List<StocksSort<Nasdaq_2013>> ss = new Calculator<Nasdaq_2013>().CalculateIncre(ll);
        return ss.stream().skip(ss.size() - 12)
                .map(stocksSort -> stocksSort.getStock())
//                .map(item -> {
//                    log.info(item.toString());
//                    return item;
//                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/index_Fmsg")// 每天的涨幅最多
    public List<Forex_2013> getForeMsg() {
        List<Forex_2013> res = F_repository.getOrderName(20130101, 20130102);
        List<List<Forex_2013>> ll = new ArrayList<>(); //两天的价格

        for (int i = 0; i < res.size() - 2; i += 2) {
            List<Forex_2013> item = new ArrayList<>();
            item.add(res.get(i));
            item.add(res.get(i + 1));
            ll.add(item);
        }
        List<StocksSort<Forex_2013>> ss = new Calculator<Forex_2013>().CalculateIncre(ll);
        return ss.stream().skip(ss.size() - 12)
                .map(stocksSort -> stocksSort.getStock())
//                .map(item -> {
//                    log.info(item.toString());
//                    return item;
//                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/index_Lmsg")// 每天的涨幅最多
    public List<Liffe> getLiffeMsg() {
        List<Liffe> res = L_repository.getOrderName(20130101, 20130102);
        List<List<Liffe>> ll = new ArrayList<>(); //两天的价格

        for (int i = 0; i < res.size() - 2; i += 2) {
            List<Liffe> item = new ArrayList<>();
            item.add(res.get(i));
            item.add(res.get(i + 1));
            ll.add(item);
        }
        List<StocksSort<Liffe>> ss = new Calculator<Liffe>().CalculateIncre(ll);
        return ss.stream().skip(ss.size() - 12)
                .map(stocksSort -> stocksSort.getStock())
//                .map(item -> {
//                    log.info(item.toString());
//                    return item;
//                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/stock/{name}") // 六个月的K线数据
    public List<Nasdaq_2013> getMoreInfo(@PathVariable String name) {
        this.name = name;
        return N_repostory.getNasdaqByDateAndName(20130101, 20130601, name).stream()
//                .map(item -> {
//                    log.info(item.toString());
//                    return item;
//                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/type/{tag}")  // 日 周 月 类型数据
    public List<Nasdaq_2013> switchData(@PathVariable String tag) {
        switch (tag) {
            case "CC-W":
                List<Nasdaq_2013> res = N_repostory.getNasdaqByDateAndName(20130101, 20130601, name);
                return new Calculator<Nasdaq_2013>().Day2longtem(res, 5);
            case "CC-M":
                List<Nasdaq_2013> res2 = N_repostory.getNasdaqByDateAndName(20130101, 20130601, name);
                return new Calculator<Nasdaq_2013>().Day2longtem(res2, 30);
            default:
                return getMoreInfo(name);
        }
    }

    @RequestMapping("/n_test")
    public Object test(@RequestParam("test") String stock) throws IOException {
        log.info(stock.toString());
        ObjectMapper mapper = new ObjectMapper();
        Stock stock1 = mapper.readValue(stock, Stock.class);
        stock1.setHigh(555555);
        return stock1;
    }


}
