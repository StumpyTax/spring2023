package com.spring2023.auth.app;

import com.spring2023.auth.app.entity.UserEntity;
import com.spring2023.auth.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with email = "+email+" not exist!"));
        return new CustomUserDetails(user) {
        };
    }
    public void save(UserEntity user) {
        userRepo.save(user);
    }
}
