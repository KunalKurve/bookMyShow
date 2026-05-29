package com.scaler.bookMyShow.controller;

import com.scaler.bookMyShow.dto.requests.CreateShowRequestDTO;
import com.scaler.bookMyShow.dto.responses.CreateShowResponseDTO;
import com.scaler.bookMyShow.dto.responses.ResponseStatus;
import com.scaler.bookMyShow.models.Show;
import com.scaler.bookMyShow.models.ShowSeat;
import com.scaler.bookMyShow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShowController {

    private ShowService showService;
    @Autowired
    public ShowController(ShowService showService){
        this.showService = showService;
    }

    public CreateShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try {
            Show show = showService.createShow(
                    requestDTO.getUserId(),
                    requestDTO.getMovieId(),
                    requestDTO.getScreenId(),
                    requestDTO.getStartTime(),
                    requestDTO.getEndTime(),
                    requestDTO.getPricingConfig(),
                    requestDTO.getFeatures());
            responseDTO.setShow(show);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTO.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDTO;
    }

    public List<ShowSeat> viewShowSeats(int showId) {

        return showService.getShowSeats(showId);
    }
}
