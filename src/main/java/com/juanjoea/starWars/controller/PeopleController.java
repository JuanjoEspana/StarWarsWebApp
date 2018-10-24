package com.juanjoea.starWars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juanjoea.starWars.dto.PeopleDto;
import com.juanjoea.starWars.service.PeopleService;

@Controller
public class PeopleController {

	@Autowired
	PeopleService peopleService;
	
	@RequestMapping("/people")
	public String home() {
		return "people";
	}
	
	@RequestMapping("/people/getAllPeople")
	public List<PeopleDto> getAllPeople() {
		return peopleService.getAllPeople();
	}

	/*@RequestMapping(value = Constants.SAVE_USER, method = RequestMethod.POST)
	public void savePeople(@RequestBody PeopleDto userDto) {
		peopleService.savePeople(userDto);
	}*/
}
