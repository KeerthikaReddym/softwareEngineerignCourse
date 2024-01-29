package com.homework.crossCountry.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homework.crossCountry.model.Result;
import com.homework.crossCountry.model.Results;
import com.homework.crossCountry.model.Runner;
import com.homework.crossCountry.model.Team;

@RestController
public class CrossCountryController {

    public static final Map<String, Team> teamsMap = new HashMap<>();

    @Autowired
	public Results results;

    public CommandInvoker commandInvoker;

    public CrossCountryController() {
        this.commandInvoker = new CommandInvoker();
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateRaceResults(@RequestBody List<String> teamNames) {
      

        initializeTeams(teamNames);
        commandInvoker.executeCommand(new CalculateResultsCommand(results, teamNames, teamsMap));

        if (results.getMessage() == null || results.getMessage().isEmpty() || results.getMessage().isBlank()) {
            // Return an error response with HTTP status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Results message is null or empty.");
        }

        // Return the results in the response body with HTTP status 200 (OK)
        return ResponseEntity.ok(results);
    }

    public void initializeTeams(List<String> teamNames) {
        teamsMap.clear();
        for (String teamName : teamNames) {
            teamsMap.putIfAbsent(teamName, new Team(teamName));
        }
        commandInvoker.executeCommand(new InitializeTeamsCommand(teamsMap, teamNames));
    }

    private static class CalculateResultsCommand implements Command {
        private final Results results;
        private final List<String> teamNames;
        private final Map<String, Team> teamsMap;

        CalculateResultsCommand(Results results, List<String> teamNames, Map<String, Team> teamsMap) {
            this.results = results;
            this.teamNames = teamNames;
            this.teamsMap = teamsMap;
        }

        @Override
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

    private static class InitializeTeamsCommand implements Command {
        private final Map<String, Team> teamsMap;
        private final List<String> teamNames;

        InitializeTeamsCommand(Map<String, Team> teamsMap, List<String> teamNames) {
            this.teamsMap = teamsMap;
            this.teamNames = teamNames;
        }

        @Override
        public void execute() {
            // implementation of initializeTeams
        }
    }

    private interface Command {
        void execute();
    }

    public static class CommandInvoker {
        private final List<Command> commands;

        public CommandInvoker() {
            this.commands = new ArrayList<>();
        }

        void executeCommand(Command command) {
            commands.add(command);
            command.execute();
        }
    }
}
