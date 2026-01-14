package com.arya.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        //1. stubbing, mocks are dumb need to tell (when....thenReturn)
        String user = "arya";

        //  teach mock to say user not exist
        when(userRepository.findByUserName(user)).thenReturn(null);

        // when this , return true
        when(userRepository.save(user)).thenReturn(true);

        //2. call the method
        String result = userService.registerUser(user);

        //3. check res
        assertEquals("Success", result);

        //4.verify whether the code call the mock or not
        //see if save() was called exactly one time with user
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void testRegisterUser_AlreadyTaken(){
        String user = "princ";

        //teaching..
        when(userRepository.findByUserName(user)).thenReturn("FoundUserObject");

        String res = userService.registerUser(user);

        assertEquals("Taken", res);

        //ensure save want called
        verify(userRepository, never()).save(anyString());
    }
}
