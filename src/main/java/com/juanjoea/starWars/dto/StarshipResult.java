package com.juanjoea.starWars.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StarshipResult extends Result {

	@JsonProperty("results")
	private List<StarshipDto> results;

	@JsonProperty("results")
	public List<StarshipDto> getResults() {
		return results;
	}
	@JsonProperty("results")
	public void setResults(List<StarshipDto> results) {
		this.results = results;
	}
	
	
}
