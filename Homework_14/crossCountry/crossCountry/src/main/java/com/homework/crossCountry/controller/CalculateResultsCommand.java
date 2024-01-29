package com.homework.crossCountry.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.homework.crossCountry.model.Result;
import com.homework.crossCountry.model.Results;
import com.homework.crossCountry.model.Runner;
import com.homework.crossCountry.model.Team;

public  class CalculateResultsCommand implements Command {
    private final Results results;
    private final List<String> teamNames;
    private final Map<String, Team> teamsMap;

    CalculateResultsCommand(Results results, List<String> teamNames, Map<String, Team> teamsMap) {
        this.results = results;
        this.teamNames = teamNames;
        this.teamsMap = teamsMap;
    }

    public void execute() {
        results.setTeams(addingValues());
    }

    private List<Result> addingValues() {
        int place = 1;
        Team team = null;
        for (String teamName : teamNames) {
            team = teamsMap.get(teamName);
            Runner runner = new Runner(place, team);
            team.addRunner(runner);
            place++;
        }
        Iterator<Map.Entry<String, Team>> iterator = teamsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Team> entry = iterator.next();
            team = entry.getValue();
            if (team.getAthletes().size() < 5) {
                iterator.remove(); // Safely remove the current entry from the map
            }
        }

        List<Result> resultsList = new ArrayList<>();
        for (Team teams : teamsMap.values()) {
            // setting all the values to the result table to easily refer it later
            Result result = new Result();
            result.setScore(teams.score());
            result.setTeamName(teams.getName());
            result.setPositionSix(teams.getTiebreakPlace());
            resultsList.add(result);
        }
        sortTeamScores(resultsList);
        return resultsList;
    }

    private void sortTeamScores(List<Result> results) {
        // Handle empty list case
        if (results.isEmpty()) {
            return;
        }

        // Initialize minScoreTeam with the first team
        Result minScoreTeam = results.get(0);

        // Iterate through the list to find the team with the least score
        for (Result result : results) {
            if (result.getScore() < minScoreTeam.getScore()) {
                minScoreTeam = result;
            }
        }

        // If there are multiple teams with equal minimum score, return null
        for (Result result : results) {
            if (result.getScore() == minScoreTeam.getScore() && !result.equals(minScoreTeam)) {
                // Check if the minimum score team has less than 5 athletes
                if (minScoreTeam.getTeam().getAthletes().size() < 5) {
                    // The other team is considered the winner
                    results.clear(); // Clear the results list
                    this.results.setMessage("team " + result.getTeamName() + " wins");
                    return;
                } else {
                    results.clear(); // Clear the results list
                    return;
                }
            }
        }

        // Update the original results list with sorted results
        results.sort(Comparator.comparingInt(Result::getPositionSix));
        results.get(0).getPositionSix();
        results.add(results.get(0));
        results.remove(results.size() - 1);
        // updating the message
        this.results.setMessage("team " + results.get(0).getTeamName() + " wins");
    }
}

