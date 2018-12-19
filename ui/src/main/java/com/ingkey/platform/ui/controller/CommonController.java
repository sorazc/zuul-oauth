package com.ingkey.platform.ui.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class CommonController {

    @RequestMapping("index")
    public String index(Principal principal) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.getPrincipal().getClass());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

}