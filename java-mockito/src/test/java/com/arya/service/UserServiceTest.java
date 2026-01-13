package com.arya.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arya.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    //mock the userRepo, fake no logic inside(like a stunt double)
    @Mock
    private UserRepository userRepository;

    //@InjectMocks , it'll create real obj and insert mocks into it from available pool, it searches... first constructor inject then field if fails.. uses reflection etc
    //equiv: userService = new UserService(userRepo)
    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser_Success(){
        
    }
}
