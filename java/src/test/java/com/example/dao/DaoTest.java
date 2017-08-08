package com.example.dao;

import com.example.DataAnanlyticsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Kenneth on 2017/8/7.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataAnanlyticsApplication.class)
@WebAppConfiguration
public class DaoTest {

    Logger log = LoggerFactory.getLogger(DaoTest.class);

    @Autowired
    DemoRepository repository;

    @Before
    public void setUp() {

    }

    @Test
    public void getStockName(){
        repository.findOne(1);
    }

    @Test
    public void getStock(){
        repository.getStockByName("a")
                .forEach(stock -> log.info(stock.toString()));
    }
}
