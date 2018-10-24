package com.juanjoea.starWars.service;

import java.util.List;

import com.juanjoea.starWars.dto.PeopleDto;

public interface PeopleService {

	 PeopleDto getPeopleById(Integer userId);
	 void savePeople(PeopleDto userDto);
	 List < PeopleDto > getAllPeople();
	 
}
