package com.demo.springbootwithredis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lx
 * @date 2021/2/16 18:41
 */
@Controller
public class TestController {


    @GetMapping("/")
    public String Index(Model model){
        model.addAttribute("hello","world");
        return "index";

    }


}
