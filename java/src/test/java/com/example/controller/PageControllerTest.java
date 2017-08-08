package com.example.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataAnanlyticsApplication.class)
@WebAppConfiguration
public class PageControllerTest {

    Logger log = LoggerFactory.getLogger(PageControllerTest.class);

    private MockMvc mvc;

    @Autowired
    private PageController controller;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getPageIndex() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/index")))
                .andExpect(status().isOk());
    }
    @Test
    public void getPageGeneric() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/index")))
                .andExpect(status().isOk());
    }
    @Test
    public void getPageTop10() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/index")))
                .andExpect(status().isOk());
    }
}
