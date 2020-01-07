package com.jg.mes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securitycontroller {

    @GetMapping("/login")
    public  String login()
    {
        return "security/login";
    }
}
