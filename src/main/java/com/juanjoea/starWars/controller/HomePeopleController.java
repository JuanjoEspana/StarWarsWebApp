package com.juanjoea.starWars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePeopleController {
    
    @RequestMapping("/people")
    public String home() {
        return "people";
    }
}
