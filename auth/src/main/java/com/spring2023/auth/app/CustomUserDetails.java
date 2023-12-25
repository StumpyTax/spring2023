package com.spring2023.auth.app;

import com.spring2023.auth.app.entity.RoleEntity;
import com.spring2023.auth.app.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleEntity> roles = user.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity role : roles) {authorities.add(new SimpleGrantedAuthority(role.getRoleName()));}
        return authorities;
    }
    public Long getId(){return user.getId();}
    @Override
    public String getPassword() {return user.getPassword();}

    @Override
    public String getUsername() {return user.getLogin();}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}