package com.scaler.bookMyShow.commands;

import com.scaler.bookMyShow.controller.UserController;
import com.scaler.bookMyShow.dto.requests.SignupRequestDto;
import com.scaler.bookMyShow.dto.responses.SignupResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUser implements Command{

    private String COMMAND_NAME = "RegisterUser";
    private UserController userController;

    @Autowired
    private RegisterUser(UserController userController){
        this.userController = userController;
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
            String username = commandFragments[1];
            String password = commandFragments[2];
            String email = commandFragments[3];
            SignupRequestDto requestDto = new SignupRequestDto();
            requestDto.setName(username);
            requestDto.setEmail(email);
            requestDto.setPassword(password);

            SignupResponseDto responseDto = userController.signUp(requestDto);
            System.out.println(responseDto.getUserId());
            System.out.println(responseDto.getResponseStatus());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
