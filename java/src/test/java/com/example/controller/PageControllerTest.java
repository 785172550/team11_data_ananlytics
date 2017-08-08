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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Kenneth on 2017/8/8.
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
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void getPageIndex() throws Exception {
        MvcResult res = mvc.perform((MockMvcRequestBuilders.get("/index")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getPageGeneric() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/generic")))
                .andExpect(status().isOk())
                .andExpect(view().name("generic"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getPageTop10() throws Exception {
        mvc.perform((MockMvcRequestBuilders.get("/topTen")))
                .andExpect(status().isOk())
                .andExpect(view().name("topTen"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
