package com.ingkey.platform.ui.controller;

import com.ingkey.platform.ui.util.AuthUtils;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @RequestMapping("principal")
    public Object principal() {
        return AuthUtils.getPrinciple();
    }

    @RequestMapping("permitAll")
    public String root() {
        return "hello, world";
    }

}