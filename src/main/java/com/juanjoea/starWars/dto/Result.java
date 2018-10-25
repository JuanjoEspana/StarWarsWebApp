package com.juanjoea.starWars.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "next",
    "previous",
    "results"
})
public abstract class Result {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
        
    @JsonProperty("count")
	public Integer getCount() {
		return count;
	}
    @JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
	}
    @JsonProperty("next")
	public String getNext() {
		return next;
	}
    @JsonProperty("next")
	public void setNext(String next) {
		this.next = next;
	}
    @JsonProperty("previous")
	public String getPrevious() {
		return previous;
	}
    @JsonProperty("previous")
	public void setPrevious(String previous) {
		this.previous = previous;
	}
    
}
