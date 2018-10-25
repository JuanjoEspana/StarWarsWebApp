package com.juanjoea.starWars.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanjoea.starWars.dto.PeopleDto;
import com.juanjoea.starWars.service.PeopleService;

@RestController
public class PeopleController {

	@Autowired
	PeopleService peopleService;
	
	List<PeopleDto> people = new ArrayList<PeopleDto>();
	
	@RequestMapping("/people/getAllPeople")
	public List<PeopleDto> getAllPeople() {
		this.people = peopleService.getAllPeople();
		return people;
	}

	@SuppressWarnings("unchecked")
    @RequestMapping("/people/sort/{field}/{order}")
	public List<PeopleDto> sort(@PathVariable final String field, @PathVariable final String order){
	    
	    if(!people.isEmpty()) {
	        this.people = (List<PeopleDto>) peopleService.sort(this.people, field, order);
	    }
	    
	    return people;
	}
}
