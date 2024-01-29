package com.homework.crossCountry.model;

import java.util.List;

public abstract class RaceCalculator {

	private Results results;
	
    public Results calculateRaceResults(List<String> teamNames) {
        initializeTeams(teamNames);
        List<Result> resultsList = calculateResults(teamNames);
        setResultsAndDetermineWinner(resultsList);
        return results;
    }

    protected abstract void initializeTeams(List<String> teamNames);

    protected abstract List<Result> calculateResults(List<String> teamNames);

    protected abstract void setResultsAndDetermineWinner(List<Result> resultsList);
}
