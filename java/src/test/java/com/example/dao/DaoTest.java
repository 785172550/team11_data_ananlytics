package com.example.dao;

import com.example.DataAnanlyticsApplication;
import com.example.model.Nasdaq_2013;
import com.example.model.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kenneth on 2017/8/7.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataAnanlyticsApplication.class)
public class DaoTest {

    Logger log = LoggerFactory.getLogger(DaoTest.class);

    @Autowired
    DemoRepository repository;

    @Autowired
    NasdaqRespostory nasdaqRespostory;

    @Before
    public void setUp() {}

    @Test
    public void getStockName(){
        repository.findOne(1);
    }

    @Test
    public void getStock(){
        repository.getStockByName("a")
                .forEach(stock -> log.info(stock.toString()));
    }

    @Test
    public void getNsdaq(){
//        nasdaqRespostory.getNasdaqByName("AAIT")
//                .forEach(nasdaq_2013 -> log.info(nasdaq_2013.toString()));
       List<Nasdaq_2013> nasdaq_2013s =
               nasdaqRespostory.getNasdaqByDate(20130930,20131011);
        nasdaq_2013s.forEach(nasdaq_2013 -> log.info(nasdaq_2013.toString()));
    }
}
