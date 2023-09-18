package com.byb.bookYourBook.serviceImpl;

import com.byb.bookYourBook.entity.Address;
import com.byb.bookYourBook.entity.Roles;
import com.byb.bookYourBook.entity.User;
import com.byb.bookYourBook.payload.SignInDto;
import com.byb.bookYourBook.payload.UserDetailsDto;
import com.byb.bookYourBook.repository.AddressReposistory;
import com.byb.bookYourBook.repository.BookListingRepository;
import com.byb.bookYourBook.repository.UserRepository;
import com.byb.bookYourBook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookListingRepository bookListingRepository;

    @Autowired
    AddressReposistory addressRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Boolean deleteUser(String email) {
        int i = userRepository.deleteByEmail(email);
        if(i > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteBookListing(int id) {
        bookListingRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean createAdmin(SignInDto signInDto) {
        Address address = new Address(signInDto.getAddressLine1(), signInDto.getAddressLine2(),signInDto.getCity(), signInDto.getState(), signInDto.getPinCode());
        address = addressRepository.save(address);
        userRepository.save(User.builder().userName(signInDto.getUserName())
                .role(Roles.ADMIN)
                .email(signInDto.getEmail())
                .phoneNumber(signInDto.getPhoneNumber())
                .address(address)
                .password(passwordEncoder.encode(signInDto.getPassword()))
                .build());
        return true;
    }

    @Override
    public List<UserDetailsDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDetailsDto> result = new ArrayList<UserDetailsDto>();
        for(User user : allUsers){
            result.add(UserDetailsDto.builder()
                            .userName(user.getUserName())
                            .email(user.getEmail())
                            .phoneNumber(user.getPhoneNumber())
                            .city(user.getAddress().getCity())
                            .state(user.getAddress().getState())
                            .addressLine1(user.getAddress().getAddressLine1())
                            .addressLine2(user.getAddress().getAddressLine2())
                            .pinCode(user.getAddress().getPinCode())
                    .build());
        }
        return result;
    }
}
