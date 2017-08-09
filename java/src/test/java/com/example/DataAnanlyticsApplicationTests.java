package com.example;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by Kenneth on 2017/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataAnanlyticsApplication.class)
@WebAppConfiguration
public class DataAnanlyticsApplicationTests {

    private MockMvc mvc;


    @Before
    public void setUp() {

    }

    @Test
    public void contextLoads() {

    }

}
