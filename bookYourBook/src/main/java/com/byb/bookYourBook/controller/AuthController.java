package com.byb.bookYourBook.controller;

import com.byb.bookYourBook.payload.LoginDto;
import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    ResponseEntity<Boolean> signin(@RequestBody SignInDto signInDto){
        return ResponseEntity.ok().body(authService.createUser(signInDto));
    }

    @GetMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok().body(authService.login(loginDto));
    }


}
