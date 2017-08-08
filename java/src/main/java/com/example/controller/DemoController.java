package com.example.controller;

import com.example.model.Post;
import com.example.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenneth on 2017/8/4.
 *
 */


@RequestMapping("/a")
@Controller
public class DemoController {

    @ResponseBody
    @RequestMapping("/demo/test")
    public Object test() {
        return new Result.Builder<String>().setMsg("100").setContent("dsafs").build();
    }

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

}
