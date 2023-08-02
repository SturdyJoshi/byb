package com.byb.bookYourBook.service;

import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.payload.UserDetailsDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean createUser(SignInDto signInDto);

    UserDetailsDto getUserDetails(String userEmail);

    UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto);

    UserDetailsDto getAllUser();
}
