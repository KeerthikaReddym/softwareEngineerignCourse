package com.homework.crossCountry.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
	 private String name;
    private List<Runner> athletes;
    private int points;

    public Team(String name) {
    	 this.name = name;
        athletes = new ArrayList<>();
        points = 0;
    }
 // Getter for team name
    public String getName() {
        return name;
    }

    public List<Runner> getAthletes() {
		return athletes;
	}
	public void setAthletes(List<Runner> athletes) {
		this.athletes = athletes;
	}
	public void addRunner(Runner runner) {
        athletes.add(runner);
    }

	/**
	 * This method is used to calculate the score
	 * @return
	 */
    public int score() {
        if (athletes.size() < 5) {
            return 0;
        }
        athletes.sort((r1, r2) -> Integer.compare(r1.getPlace(), r2.getPlace()));
        for (int i = 0; i < 5; i++) {
            points += athletes.get(i).getPlace();
        }
        return points;
    }

    /**
     * This method is used to calculate the place when there is tie
     * @return
     */
    public int getTiebreakPlace() {
        if (athletes.size() >= 6) {
            return athletes.get(5).getPlace();
        }
        return 0;
    }
}
