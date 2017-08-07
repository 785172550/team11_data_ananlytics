package com.example.controller;

import com.example.DataAnanlyticsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kenneth on 2017/8/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataAnanlyticsApplication.class)
@WebAppConfiguration
public class ControllerTest {

    Logger log = LoggerFactory.getLogger(ControllerTest.class);

    private MockMvc mvc;

    @Autowired
    private DemoController demoController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(demoController).build();
    }

    @Test
    public void getJson() throws Exception {
//                mvc.perform(MockMvcRequestBuilders.post("/post")
//                .param("title","test")
//                .param("content","test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn();
        mvc.perform(MockMvcRequestBuilders.get("/test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
