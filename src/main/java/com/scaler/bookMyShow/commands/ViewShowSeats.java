package com.scaler.bookMyShow.commands;

import com.scaler.bookMyShow.controller.ShowController;
import com.scaler.bookMyShow.models.ShowSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewShowSeats implements Command {

    private final String COMMAND_NAME = "ViewShowSeats";

    private final ShowController showController;

    @Autowired
    public ViewShowSeats(ShowController showController) {

        this.showController = showController;
    }

    @Override
    public boolean matches(String input) {

        String[] fragments = input.split(" ");

        return fragments[0].equalsIgnoreCase(COMMAND_NAME);
    }

    @Override
    public void execute(String input) {

        String[] fragments = input.split(" ");

        int showId = Integer.parseInt(fragments[1]);

        List<ShowSeat> showSeats = showController.viewShowSeats(showId);

        for(ShowSeat showSeat : showSeats) {

            System.out.println(
                    showSeat.getSeat().getSeatNumber() + " -> "
                            + showSeat.getShowSeatStatus()
            );
        }
    }
}
