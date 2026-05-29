package com.scaler.bookMyShow.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {

    private List<Command> supportedCommands;

    @Autowired
    public CommandExecutor(
            RegisterUser registerUser,
            CreateShow createShow,
            MakeBooking makeBooking,
            MakePayment makePayment,
            ViewShowSeats viewShowSeats
    ){
        this.supportedCommands = new ArrayList<>();
        supportedCommands.add(registerUser);
        supportedCommands.add(createShow);
        supportedCommands.add(makeBooking);
        supportedCommands.add(makePayment);
        supportedCommands.add(viewShowSeats);
    }

    public void addCommand(Command command){
        supportedCommands.add(command);
    }

    public void removeCommand(Command command){
        supportedCommands.remove(command);
    }

    public void execute(String input){
        for(Command command : supportedCommands){
            if(command.matches(input)) {
                command.execute(input);
            }
        }
    }
}
