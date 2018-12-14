package cn.zc.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("user")
    public String user(){
        return "user";
    }

}

