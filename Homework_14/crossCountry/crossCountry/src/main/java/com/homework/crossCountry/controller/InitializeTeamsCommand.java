package com.homework.crossCountry.controller;



import java.util.List;
import java.util.Map;

import com.homework.crossCountry.model.Team;

public class InitializeTeamsCommand implements Command {
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


