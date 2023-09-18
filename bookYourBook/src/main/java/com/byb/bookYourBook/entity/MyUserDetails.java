package com.byb.bookYourBook.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public MyUserDetails(User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
//
//        this.authorities.add(simpleGrantedAuthority);
//        System.out.println(user.getRole().toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
