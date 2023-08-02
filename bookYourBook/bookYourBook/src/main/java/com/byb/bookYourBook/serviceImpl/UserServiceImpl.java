package com.byb.bookYourBook.serviceImpl;

import com.byb.bookYourBook.dto.SignInDto;
import com.byb.bookYourBook.dto.UserDetailsDto;
import com.byb.bookYourBook.model.Address;
import com.byb.bookYourBook.model.Roles;
import com.byb.bookYourBook.model.User;
import com.byb.bookYourBook.repository.AddressReposistory;
import com.byb.bookYourBook.repository.UserRepository;
import com.byb.bookYourBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressReposistory addressReposistory;

    @Override
    public boolean createUser(SignInDto signInDto) {
        Address address = new Address(signInDto.getAddressLine1(), signInDto.getAddressLine2(),signInDto.getCity(), signInDto.getState(), signInDto.getPinCode());
        address = addressReposistory.save(address);
        userRepository.save(User.builder().userName(signInDto.getUserName())
                .role(Roles.USER)
                .email(signInDto.getEmail())
                .phoneNumber(signInDto.getPhoneNumber())
                .address(address)
                .password(signInDto.getPassword())
                .build());
        return true;
    }

    @Override
    public UserDetailsDto getUserDetails(String userEmail) {
        Optional<User> userOpt = userRepository.findByEmail(userEmail);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            return UserDetailsDto.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .state(user.getAddress().getState())
                    .addressLine1(user.getAddress().getAddressLine1())
                    .addressLine2(user.getAddress().getAddressLine2())
                    .city(user.getAddress().getCity())
                    .pinCode(user.getAddress().getPinCode())
                    .build();
        }
        else {
            return null;
        }
    }

    @Override
    public UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto) {
        return null;
    }

    @Override
    public UserDetailsDto getAllUser() {
        return null;
    }
}
