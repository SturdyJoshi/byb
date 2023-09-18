package com.byb.bookYourBook.service;

import com.byb.bookYourBook.payload.UserDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDetailsDto getUserDetails();

    UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto);

    List<UserDetailsDto> getAllUser();
}
