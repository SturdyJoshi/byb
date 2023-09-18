package com.byb.bookYourBook.service;

import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.payload.UserDetailsDto;

import java.util.List;

public interface AdminService {

    Boolean deleteUser(String email);
    Boolean deleteBookListing(int id);
    boolean createAdmin(SignInDto signInDto);

    List<UserDetailsDto> getAllUsers();

}
