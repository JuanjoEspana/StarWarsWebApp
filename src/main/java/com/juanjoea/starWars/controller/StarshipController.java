package com.juanjoea.starWars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StarshipController {
	 @RequestMapping("/starship")
	 public String home() {
	  return "starship";
	 }
}
