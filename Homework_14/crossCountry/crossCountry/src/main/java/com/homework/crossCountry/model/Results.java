package com.homework.crossCountry.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Results {
    private List<Result> teams;
    private String message;
    // Default constructor
    public Results() {
    }
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Result> getTeams() {
		return teams;
	}

	public void setTeams(List<Result> teams) {
		this.teams = teams;
	}

	/**
	 * 
	 * @param teams
	 */
	public Results(List<Result> teams) {
        this.teams = teams;
    }

	

   
}

