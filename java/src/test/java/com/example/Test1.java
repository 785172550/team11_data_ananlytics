package com.example;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/8/4.
 */


public class Test1 {

    Logger log = LoggerFactory.getLogger(Test1.class);

    @Before
    public void setUp(){

    }
    @Test
    public  void  test(){
        log.info("sdafsdfasdfsadf");
    }
}
