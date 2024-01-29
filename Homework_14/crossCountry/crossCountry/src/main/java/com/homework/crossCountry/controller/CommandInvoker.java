package com.homework.crossCountry.controller;

import java.util.ArrayList;
import java.util.List;

public  class CommandInvoker {
    private final List<Command> commands;

    public CommandInvoker() {
        this.commands = new ArrayList<>();
    }

    void executeCommand(Command command) {
        commands.add(command);
        command.execute();
    }
}