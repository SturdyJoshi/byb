package com.byb.bookYourBook.serviceImpl;

import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.payload.UserDetailsDto;
import com.byb.bookYourBook.entity.Address;
import com.byb.bookYourBook.entity.Roles;
import com.byb.bookYourBook.entity.User;
import com.byb.bookYourBook.repository.AddressReposistory;
import com.byb.bookYourBook.repository.UserRepository;
import com.byb.bookYourBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        Optional<User> userOpt = userRepository.findByEmail(userDetailsDto.getEmail());
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            if(userDetailsDto.getUserName() != null){
                user.setUserName(userDetailsDto.getUserName());
            }
            if (userDetailsDto.getEmail() != null) {
                user.setEmail(userDetailsDto.getEmail());
            }

            if (userDetailsDto.getPhoneNumber() != 0) {
                user.setPhoneNumber(userDetailsDto.getPhoneNumber());
            }

            if (userDetailsDto.getAddressLine1() != null) {
                user.getAddress().setAddressLine1(userDetailsDto.getAddressLine1());
            }

            if (userDetailsDto.getAddressLine2() != null) {
                user.getAddress().setAddressLine2(userDetailsDto.getAddressLine2());
            }

            if (userDetailsDto.getCity() != null) {
                user.getAddress().setCity(userDetailsDto.getCity());
            }

            if (userDetailsDto.getState() != null) {
                user.getAddress().setState(userDetailsDto.getState());
            }

            if (userDetailsDto.getPinCode() != 0) {
                user.getAddress().setPinCode(userDetailsDto.getPinCode());
            }
            user = userRepository.save(user);

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
        else{
            return null;
        }
    }

    @Override
    public List<UserDetailsDto> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        ArrayList<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        for(User user : allUsers){
            userDetailsDtoList.add(UserDetailsDto.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .state(user.getAddress().getState())
                    .addressLine1(user.getAddress().getAddressLine1())
                    .addressLine2(user.getAddress().getAddressLine2())
                    .city(user.getAddress().getCity())
                    .pinCode(user.getAddress().getPinCode())
                    .build());
        }
        return userDetailsDtoList;
    }
}
