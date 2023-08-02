package com.byb.bookYourBook.service;

import com.byb.bookYourBook.dto.SignInDto;
import com.byb.bookYourBook.dto.UserDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean createUser(SignInDto signInDto);

    UserDetailsDto getUserDetails(String userEmail);

    UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto);

    List<UserDetailsDto> getAllUser();
}
