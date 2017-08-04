package com.example;


import com.example.controller.DemoController;
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

    @Autowired
    private DemoController demoController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(demoController).build();
    }

    @Test
    public void contextLoads() {

    }

}
