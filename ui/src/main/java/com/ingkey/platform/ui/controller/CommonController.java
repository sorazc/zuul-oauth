package com.ingkey.platform.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

}