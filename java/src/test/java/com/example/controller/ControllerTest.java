package com.example.controller;

import com.example.DataAnanlyticsApplication;
import com.example.dao.DemoRepository;
import com.example.model.Stock;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    private StockController controller;

    @Autowired
    private DemoRepository repository;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getStock() throws Exception {

        MvcResult res = mvc.perform(MockMvcRequestBuilders.get("/stock/apple")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(content().json("'name':'apple'"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getStockTop10() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/stock_10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getStockSpecial() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/stock_special")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
//        repository.getStockByName("a").forEach(stock -> log.info(stock.getName()));
    }
}
