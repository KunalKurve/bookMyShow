package com.scaler.bookMyShow.controller;

import com.scaler.bookMyShow.dto.ResponseStatus;
import com.scaler.bookMyShow.dto.SignupRequestDto;
import com.scaler.bookMyShow.dto.SignupResponseDto;
import com.scaler.bookMyShow.models.User;
import com.scaler.bookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public SignupResponseDto signUp(SignupRequestDto requestDto){
        SignupResponseDto responseDto = new SignupResponseDto();
        try {
                User user = userService.registerUser(
                        requestDto.getName(),
                        requestDto.getEmail(),
                        requestDto.getPhone(),
                        requestDto.getPassword()
                );
                responseDto.setUserId(user.getId());
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
                responseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDto;
    }

}
