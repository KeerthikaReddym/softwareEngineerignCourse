package com.homework.crossCountry.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultRaceCalculator extends RaceCalculator {

    private static final Map<String, Team> teamsMap = new HashMap<>();

    @Autowired
    private Results results;

    @Override
    protected void initializeTeams(List<String> teamNames) {
        teamsMap.clear();
        for (String teamName : teamNames) {
            teamsMap.putIfAbsent(teamName, new Team(teamName));
        }
    }

    @Override
    protected List<Result> calculateResults(List<String> teamNames) {
        List<Result> resultsList = addingValues(teamNames);
        results.setTeams(resultsList);
        return resultsList;
    }

    @Override
    protected void setResultsAndDetermineWinner(List<Result> resultsList) {
        Result winningTeam = determineWinner(resultsList);
        if (winningTeam != null) {
            results.setMessage("team " + winningTeam.getTeamName() + " wins");
        } else {
            tiebreak(resultsList);
        }
    }

    private List<Result> addingValues(List<String> teamNames) {
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
        return resultsList;
    }

    private Result determineWinner(List<Result> results) {
        // Handle empty list case
        if (results.isEmpty()) {
            return null;
        }

        // Sort results by score in ascending order
        results.sort(Comparator.comparingInt(Result::getScore));

        // If there are multiple teams with equal minimum score, return null
        if (results.get(0).getScore() == results.get(1).getScore()) {
            return null;
        }

        // Return the team with the least score
        return results.get(0);
    }

    /**
     * This method is used to calculate the winner when there is a tiebreak
     * 
     * @param resultsList
     */
    private void tiebreak(List<Result> resultsList) {
        // sorting only if the list has more than one team
        if (resultsList.size() > 1) {
            resultsList.sort(Comparator.comparingInt(Result::getPositionSix));
            // updating the message
            results.setMessage("team " + resultsList.get(0).getTeamName() + " wins");
        }
    }
}
