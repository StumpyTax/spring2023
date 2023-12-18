package com.spring2023.auth.extern.controllers;

import com.spring2023.auth.app.CustomUserDetails;
import com.spring2023.auth.app.CustomUserDetailsService;
import com.spring2023.auth.app.TokenService;
import com.spring2023.auth.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json")
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private CustomUserDetailsService usrDetailsService;


    record LoginRequest(String username, String password) {};
    record LoginResponse(String message, String access_jwt_token, String refresh_jwt_token) {};
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.username, request.password);
        Authentication auth = authManager.authenticate(authenticationToken);

        CustomUserDetails user = (CustomUserDetails) usrDetailsService.loadUserByUsername(request.username);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);

        return new LoginResponse("User with email = "+ request.username + " successfully logined!"

                , access_token, refresh_token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        var user = new UserEntity();
        user.setEmail(request.username);
        var encoder = new BCryptPasswordEncoder();
        var encodedPass = encoder.encode(request.password);
        user.setPassword(encodedPass);
        usrDetailsService.save(user);
        return ResponseEntity.ok().build();
    }

    record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {};
    @GetMapping("/token/refresh")
    public RefreshTokenResponse refreshToken(HttpHeaders request) {
        String headerAuth = request.getFirst("Authorization");
        String refreshToken = headerAuth.substring(7, headerAuth.length());

        String email = tokenService.parseToken(refreshToken).getSubject();
        CustomUserDetails user = (CustomUserDetails) usrDetailsService.loadUserByUsername(email);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);

        return new RefreshTokenResponse(access_token, refresh_token);
    }
    @PostMapping("/validate")
    public String validate(@RequestParam String token){
        return tokenService.parseToken(token).toString();
    }
}
