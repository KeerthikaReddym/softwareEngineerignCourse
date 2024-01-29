package com.homework.crossCountry.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homework.crossCountry.model.Result;
import com.homework.crossCountry.model.Runner;
import com.homework.crossCountry.model.Team;
import com.homework.crossCountry.service.Results;

@RestController
public class CrossCountryController {

	private static final Map<String, Team> teamsMap = new HashMap<>();

	@Autowired
	Results results;

	/**
	 * This method is called from endpoint to return the team score and printing winning team
	 * @param teamNames
	 * @return
	 */
	// Endpoint to calculate race results
    @PostMapping("/calculate")
    public Results calculateRaceResults(@RequestBody List<String> teamNames) {
        initializeTeams(teamNames);
        List<Result> resultsList = calculateResults(teamNames);
        setResultsAndDetermineWinner(resultsList);
        return results;
    }

    // Initializes teams based on provided team names
    private void initializeTeams(List<String> teamNames) {
        teamsMap.clear();
        for (String teamName : teamNames) {
            teamsMap.putIfAbsent(teamName, new Team(teamName));
        }
    }

    // Calculates results for the teams
    private List<Result> calculateResults(List<String> teamNames) {
        List<Result> resultsList = addingValues(teamNames);
        results.setTeams(resultsList);
        return resultsList;
    }

    // Sets the results and determines the winning team or tiebreak
    private void setResultsAndDetermineWinner(List<Result> resultsList) {
        String winningTeam = sortTeamScores(resultsList);
        if (winningTeam != null) {
            results.setMessage("team " + winningTeam + " wins");
        } else {
            tiebreak(resultsList);
        }
    }

	/**
	 * This method is used to add the values to the request classes
	 * @param teamNames
	 * @return
	 */
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

		List<Result> resultsList = new ArrayList<Result>();
		for (Team teams : teamsMap.values()) {
			//setting all the values to the result table to easily refer it later
			Result result = new Result();
			result.setScore(teams.score());
			result.setTeamName(teams.getName());
			result.setPositionSix(teams.getTiebreakPlace());
			resultsList.add(result);
		}
		return resultsList;
	}

	//This method is used to calculate the scores
	public String sortTeamScores(List<Result> results) {
		// Handle empty list case
		if (results.isEmpty()) {
			return null;
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
				return null;
			}
		}

		// Return the team name with the least score
		return minScoreTeam.getTeamName();
	}

	/**
	 * This method is used to calculate the winner			
 when there is a tie break
	 * @param resultsList
	 * @return
	 */
	private Results tiebreak(List<Result> resultsList) {
		// sorting only if the list has more than one teams
		if (resultsList.size() > 1) {
			resultsList.sort(Comparator.comparingInt(teams -> teams.getPositionSix()));
			resultsList.get(0).getPositionSix();
			resultsList.add(resultsList.get(0));
			resultsList.remove(resultsList.size()-1);
			// updating the message
			results.setMessage("team " + resultsList.get(0).getTeamName() + " wins");
		}
		return results;
	}
}
