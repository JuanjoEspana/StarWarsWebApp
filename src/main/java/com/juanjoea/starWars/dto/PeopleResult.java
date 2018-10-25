package com.juanjoea.starWars.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeopleResult extends Result {

	@JsonProperty("results")
    private List<PeopleDto> results;

	@JsonProperty("results")
	public List<PeopleDto> getResults() {
		return results;
	}

	@JsonProperty("results")
	public void setResults(List<PeopleDto> results) {
		this.results = results;
	}
	
	
}
