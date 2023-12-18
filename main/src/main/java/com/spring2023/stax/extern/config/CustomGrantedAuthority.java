package com.spring2023.stax.extern.config;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

    private final String authority;

    public CustomGrantedAuthority(String authority){
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
