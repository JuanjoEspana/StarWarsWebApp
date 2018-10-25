package com.juanjoea.starWars.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.juanjoea.starWars.dto.PeopleDto;
import com.juanjoea.starWars.dto.PeopleResult;
import com.juanjoea.starWars.dto.StarshipDto;
import com.juanjoea.starWars.dto.StarshipResult;
import com.juanjoea.starWars.service.PeopleService;

@Service
public class PeopleServiceImpl extends SortableEntityService implements PeopleService{

	@Override
	public List<PeopleDto> getAllPeople() {
		final List<PeopleDto> peopleList = new ArrayList<PeopleDto>();
		PeopleResult result = getPeopleFromRestServer("https://swapi.co/api/people/");
		
		peopleList.addAll(result.getResults());
		
		while (null != result.getNext()) {
			result = getPeopleFromRestServer(result.getNext());
			peopleList.addAll(result.getResults());
		}
	    
		for(final PeopleDto people : peopleList) {
			final List<String> starshipList = new ArrayList<String>();
		
			for(final String starshipUrl : people.getStarships()) {
				final StarshipDto starshipResult = 
						getStarshipFromRestServer(starshipUrl);
				if(null != starshipResult){
					starshipList.add(starshipResult.getName());
				}
			}
			if(starshipList.isEmpty()) {
				people.setStarships(null);
			} else {
				people.setStarships(starshipList);
			}
		}
		
		return peopleList;
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
