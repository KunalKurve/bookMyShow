package com.scaler.bookMyShow.controller;

import com.scaler.bookMyShow.dto.requests.LoginRequestDto;
import com.scaler.bookMyShow.dto.responses.LoginResponseDto;
import com.scaler.bookMyShow.dto.responses.ResponseStatus;
import com.scaler.bookMyShow.dto.requests.SignupRequestDto;
import com.scaler.bookMyShow.dto.responses.SignupResponseDto;
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
                        requestDto.getPassword()
                );
                responseDto.setUserId(user.getId());
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
                responseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try {
            boolean isLoggedIn = userService.login(
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            responseDto.setLoggedIn(isLoggedIn);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILED);
        }
        return responseDto;
    }

}
