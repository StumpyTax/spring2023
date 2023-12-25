package com.spring2023.stax.extern.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CustomAuthentication implements Authentication {

    private String token;
    private boolean isAuthenticated;
    private List<GrantedAuthority> authorities;

    public CustomAuthentication(String claimsString, String token) {
        setAuthenticated(true);
        var claims = new HashMap<String, String>();
        claimsString = claimsString.replaceAll("[\"{}]", "");
        var keyValuePairs = claimsString.split(",");
        for (var keyValue:keyValuePairs) {
            var keyValueArray = keyValue.split(":");
            claims.put(keyValueArray[0], keyValueArray[1]);
        }
        this.token = token;
        authorities = new ArrayList<>();
        var scopes = claims.get("scope");
        authorities.add(new SimpleGrantedAuthority(scopes));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
