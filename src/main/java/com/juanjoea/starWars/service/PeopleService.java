package com.juanjoea.starWars.service;

import java.util.List;

import com.juanjoea.starWars.dto.PeopleDto;

public interface PeopleService extends SortableEntityService{

	 List < PeopleDto > getAllPeople();
	 
}
