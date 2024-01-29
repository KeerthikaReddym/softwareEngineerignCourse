package com.homework.crossCountry.model;

public class Runner {
	
	private int place;
    private Team team;

    public Runner(int place, Team team) {
        this.place = place;
        this.team = team;
    }

    public int getPlace() {
        return place;
    }

    public Team getTeam() {
        return team;
    }
}
