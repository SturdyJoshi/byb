package com.byb.bookYourBook.service;

import com.byb.bookYourBook.entity.User;
import com.byb.bookYourBook.payload.LoginDto;
import com.byb.bookYourBook.payload.SignInDto;

public interface AuthService {

    boolean createUser(SignInDto signInDto);

    String login(LoginDto loginDto);

    public User getCurrentUser();

}
