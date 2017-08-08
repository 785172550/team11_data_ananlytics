package com.example.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kenneth on 2017/8/7.
 *
 */

public class DaoTest {

    @Autowired
    DemoRepository repository;

    @Before
    public void setUp() {

    }

    @Test
    public void getStockName(){
        repository.findOne(1L);
    }
}
