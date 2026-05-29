package com.scaler.bookMyShow.commands;

public interface Command {

    boolean matches(String input);

    void execute(String input);
}
