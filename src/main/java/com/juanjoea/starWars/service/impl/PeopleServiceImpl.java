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
import com.juanjoea.starWars.dto.Result;
import com.juanjoea.starWars.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Override
	public PeopleDto getPeopleById(Integer userId) {
		//return UserConverter.entityToDto(userRepository.getOne(userId));
		return null;
	}

	@Override
	public void savePeople(PeopleDto userDto) {
		//userRepository.save(UserConverter.dtoToEntity(userDto));
	}

	@Override
	public List<PeopleDto> getAllPeople() {
		final List<PeopleDto> peopleList = new ArrayList<PeopleDto>();
		Result result = getPeopleFromRestServer("https://swapi.co/api/people/");
		
		peopleList.addAll(result.getResults());
		
		while (null != result.getNext()) {
			result = getPeopleFromRestServer(result.getNext());
			peopleList.addAll(result.getResults());
		}
	    
		return peopleList;
	}

	private Result getPeopleFromRestServer(final String urlOverHttps) {
		CloseableHttpClient httpClient
	      = HttpClients.custom()
	        .setSSLHostnameVerifier(new NoopHostnameVerifier())
	        .build();
	    HttpComponentsClientHttpRequestFactory requestFactory 
	      = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    
	    ResponseEntity<Result> response 
	      = new RestTemplate(requestFactory).exchange(
	      urlOverHttps, HttpMethod.GET, null, Result.class);

	    return response.getBody();
	}

	
}
