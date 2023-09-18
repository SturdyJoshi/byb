package com.byb.bookYourBook.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        String authorities = (String) jwt.getClaims().get("roles");

        return Arrays.stream(authorities.split(",")).map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }
}
