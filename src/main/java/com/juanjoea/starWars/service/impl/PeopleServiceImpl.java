package com.juanjoea.starWars.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.juanjoea.starWars.dto.PeopleDto;
import com.juanjoea.starWars.dto.PeopleResult;
import com.juanjoea.starWars.dto.StarshipDto;
import com.juanjoea.starWars.service.PeopleService;

@Service
public class PeopleServiceImpl extends SortableEntityService implements PeopleService{

	private static final Logger log = LoggerFactory.getLogger(PeopleServiceImpl.class);
	
	@Override
	public List<PeopleDto> getAllPeople() {
		final List<PeopleDto> peopleList = new ArrayList<PeopleDto>();
		PeopleResult result = getPeopleFromRestServer("https://swapi.co/api/people/");
		
		log.info("Initial Star Wars People recovered from Rest server");
		
		peopleList.addAll(result.getResults());
		
		while (null != result.getNext()) {
			log.info("Get more people from rest server");
			result = getPeopleFromRestServer(result.getNext());
			peopleList.addAll(result.getResults());
		}
	    
		log.info("All people from rest server recovered.");
		getStarshipForPeople(peopleList);
		
		return peopleList;
	}

	private void getStarshipForPeople(final List<PeopleDto> peopleList) {
		log.info("Start getStarshipForPeople method");
		for(final PeopleDto people : peopleList) {
			log.debug("Look for starships for "+people.getName());
			final List<String> starshipList = new ArrayList<String>();
		
			for(final String starshipUrl : people.getStarships()) {
				final StarshipDto starshipResult = 
						getStarshipFromRestServer(starshipUrl);
				if(null != starshipResult){
					log.debug("Found starship "+starshipResult.getName());
					starshipList.add(starshipResult.getName());
				}
			}
			if(starshipList.isEmpty()) {
				people.setStarships(null);
			} else {
				people.setStarships(starshipList);
			}
		}
		
		log.info("End getStarshipForPeople method");
	}

	private PeopleResult getPeopleFromRestServer(final String urlOverHttps) {
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    
	    ResponseEntity<PeopleResult> response 
	      = new RestTemplate(requestFactory).exchange(
	      urlOverHttps, HttpMethod.GET, null, PeopleResult.class);

	    return response.getBody();
	}
	
	private StarshipDto getStarshipFromRestServer(final String urlOverHttps) {
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    
	    ResponseEntity<StarshipDto> response 
	      = new RestTemplate(requestFactory).exchange(
	      urlOverHttps, HttpMethod.GET, null, StarshipDto.class);

	    return response.getBody();
	}
}
