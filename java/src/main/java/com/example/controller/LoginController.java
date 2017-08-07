package com.example.controller;

import com.example.model.Result;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by Kenneth on 2017/8/7.
 *
 */

@Controller
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public Result<Object> login(@RequestParam("username") String userName,
                        @RequestParam("passwd") String passwd){
        return new Result.Builder<Object>().setStatus(200).
                setContent(userName + ":" + passwd).build();
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
