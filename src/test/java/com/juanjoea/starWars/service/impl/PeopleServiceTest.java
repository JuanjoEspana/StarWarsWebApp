package com.juanjoea.starWars.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.juanjoea.starWars.Application;
import com.juanjoea.starWars.dto.PeopleDto;
import com.juanjoea.starWars.service.PeopleService;

@RunWith(SpringRunner.class)
@SpringBootTest(		  
		  classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PeopleServiceTest {

	static List<PeopleDto> peopleList = new ArrayList<PeopleDto>();	
	
	@Autowired
	PeopleService service;
	
	@Test
	public void testGetAllPeople() {
		peopleList = service.getAllPeople();
		
		Assert.assertNotNull(peopleList);
		Assert.assertEquals(87, peopleList.size());
		Assert.assertTrue("Luke Skywalker".equals(peopleList.get(0).getName()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSortAscByName() {
		peopleList = (List<PeopleDto>) service.sort(peopleList, "name", "true");
		
		Assert.assertNotNull(peopleList);
		Assert.assertEquals(87, peopleList.size());
		Assert.assertTrue("Ackbar".equals(peopleList.get(0).getName()));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSortDescByName() {
		peopleList = (List<PeopleDto>) service.sort(peopleList, "name", "false");
		
		Assert.assertNotNull(peopleList);
		Assert.assertEquals(87, peopleList.size());
		Assert.assertTrue("Zam Wesell".equals(peopleList.get(0).getName()));
	}
		
	@SuppressWarnings("unchecked")
	@Test
	public void testSortDescByDate() {
		peopleList = (List<PeopleDto>) service.sort(peopleList, "created", "false");
		
		Assert.assertNotNull(peopleList);		
		Assert.assertTrue("Captain Phasma".equals(peopleList.get(0).getName()));
	}
}

