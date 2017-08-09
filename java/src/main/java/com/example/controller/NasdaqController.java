package com.example.controller;

import com.example.dao.LiffeRepository;
import com.example.dao.NasdaqRespostory;
import com.example.model.Liffe;
import com.example.model.Nasdaq_2013;
import com.example.model.Security;
import com.example.model.Stock;
import com.example.utils.DemoUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kenneth on 2017/8/9.
 */

@RestController
public class NasdaqController {

    Logger log = LoggerFactory.getLogger(NasdaqController.class);

    @Autowired
    NasdaqRespostory respostory;

    @Autowired
    LiffeRepository repository1;


    @RequestMapping("/index_msg")
    public List<Nasdaq_2013> getIndexMsg(){
       return respostory.getNasdaqByDate(20130101,20130301).stream()
               .map(item -> {
                    log.info(item.toString());
                    return item;
                }).limit(12).collect(Collectors.toList());
//        return repository1.getNasdaqByDate(20110103,20110301).stream()
//                .map(item -> {
//                    log.debug(item.toString());
//                    return item;
//                })
//                .limit(12).collect(Collectors.toList());
    }

    @RequestMapping("/n_test")
    public Object test(@RequestParam("test")String stock) throws IOException {
        log.info(stock.toString());
        ObjectMapper mapper = new ObjectMapper();
        Stock stock1 = mapper.readValue(stock,Stock.class);
        stock1.setHigh(555555);
        return stock1;
    }
}
