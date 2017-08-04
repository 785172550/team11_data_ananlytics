package com.example.controller;

import com.example.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenneth on 2017/8/4.
 *
 */

@RequestMapping("/demo")
@RestController
public class DemoController {

    @RequestMapping("/test")
    public Object test() {
//        return new Result.Builder<String>().setMsg("ok").setContent("hello world").build();
        Map<String, String> res = new HashMap();
        res.put("test key", "test content");
        return res;
    }
}
