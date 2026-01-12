package com.arya.service;

import com.arya.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    //contructor injecting
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String registerUser(String username){
        if(username == null || username.trim().isEmpty()){
            return "Invalid";
        }

        //check if exists
        if(userRepository.findByUserName(username) != null){
            return "Taken";
        }

        //try saving..
        boolean isSaved = userRepository.save(username);
        return isSaved? "Success": "DB_Fail"; 
    }
}
