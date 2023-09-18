package com.byb.bookYourBook.serviceImpl;

import com.byb.bookYourBook.entity.Address;
import com.byb.bookYourBook.entity.Roles;
import com.byb.bookYourBook.entity.User;
import com.byb.bookYourBook.payload.LoginDto;
import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.repository.AddressReposistory;
import com.byb.bookYourBook.repository.UserRepository;
import com.byb.bookYourBook.security.JwtProvider;
import com.byb.bookYourBook.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AddressReposistory addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public boolean createUser(SignInDto signInDto) {
        Address address = new Address(signInDto.getAddressLine1(), signInDto.getAddressLine2(),signInDto.getCity(), signInDto.getState(), signInDto.getPinCode());
        address = addressRepository.save(address);
        userRepository.save(User.builder().userName(signInDto.getUserName())
                .role(Roles.USER)
                .email(signInDto.getEmail())
                .phoneNumber(signInDto.getPhoneNumber())
                .address(address)
                .password(passwordEncoder.encode(signInDto.getPassword()))
                .build());
        return true;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return token;
    }

    @Override
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getSubject()).get();
    }

}
