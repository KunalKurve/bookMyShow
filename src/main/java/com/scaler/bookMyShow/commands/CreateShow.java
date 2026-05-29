package com.scaler.bookMyShow.commands;

import com.scaler.bookMyShow.controller.ShowController;
import com.scaler.bookMyShow.dto.requests.CreateShowRequestDTO;
import com.scaler.bookMyShow.dto.responses.CreateShowResponseDTO;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateShow implements Command{

    private String COMMAND_NAME = "CreateShow";
    private ShowController showController;

    @Autowired
    private CreateShow(ShowController showController){
        this.showController = showController;
    }

    @Override
    public boolean matches(String input) {
        String[] commandFragments = input.split(" ");
        if(commandFragments[0].equalsIgnoreCase(COMMAND_NAME)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        String[] commandFragments = input.split(" ");
        try{
            CreateShowRequestDTO createShowRequestDTO = getCreateShowRequestDTO(commandFragments);

            CreateShowResponseDTO createShowResponseDTO = showController.createShow(createShowRequestDTO);

            System.out.println("Show created with ID: " + createShowResponseDTO.getShow().getId());
            System.out.println(createShowResponseDTO.getResponseStatus());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    private static CreateShowRequestDTO getCreateShowRequestDTO(String[] commandFragments) {
        int userId = Integer.parseInt(commandFragments[1]);
        int movieId = Integer.parseInt(commandFragments[2]);
        int screenId = Integer.parseInt(commandFragments[2]);

        CreateShowRequestDTO createShowRequestDTO = new CreateShowRequestDTO();
        createShowRequestDTO.setUserId(userId);
        createShowRequestDTO.setMovieId(movieId);
        createShowRequestDTO.setScreenId(screenId);
//        createShowRequestDTO.setStartTime();
//        createShowRequestDTO.setEndTime();
//        createShowRequestDTO.setPricingConfig();
//        createShowRequestDTO.setFeatures();
        return createShowRequestDTO;
    }
}
