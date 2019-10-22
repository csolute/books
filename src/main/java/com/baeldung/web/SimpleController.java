package com.baeldung.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {

    @Value("${spring.application.name}")
    String appName;
    
    @Value("${server.port}")
    String port;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("port", port);
        
        return "home";
    }
}
