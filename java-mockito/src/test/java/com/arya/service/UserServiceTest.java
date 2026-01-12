package com.arya.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arya.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    //mock the userRepo, fake no logic in
    @Mock
    private UserRepository userRepository;
}
