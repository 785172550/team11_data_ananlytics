package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/generic")
    public String getGeneric(){
        return "generic";
    }

    @RequestMapping("/topTen")
    public String getTop10(){
        return "topTen";
    }

    @RequestMapping("/CORR")
    public String getCORR(){
        return "";
    }
}
