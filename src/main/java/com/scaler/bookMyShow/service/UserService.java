package com.scaler.bookMyShow.service;

import com.scaler.bookMyShow.exceptions.UserNotFoundException;
import com.scaler.bookMyShow.models.User;
import com.scaler.bookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String name, String email, String phone, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new RuntimeException("User is already registered");
        }

        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public boolean login(String email, String password) throws UserNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User does not exist"));

        return passwordEncoder.matches(password, user.getPassword());

    }
}
