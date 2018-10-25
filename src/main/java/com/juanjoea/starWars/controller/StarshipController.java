package com.juanjoea.starWars.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarshipController {
	 @RequestMapping("/starship")
	 public String home() {
	  return "starship";
	 }
}
