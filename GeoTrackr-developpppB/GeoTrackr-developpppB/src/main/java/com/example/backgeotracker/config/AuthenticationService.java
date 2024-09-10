package com.example.backgeotracker.config;

import com.example.backgeotracker.Auth.AuthenticationRequest;
import com.example.backgeotracker.Auth.AuthenticationResponse;
import com.example.backgeotracker.Auth.RegisterRequest;

import com.example.backgeotracker.Repositories.userRepository;
import lombok.RequiredArgsConstructor;
import  com.example.backgeotracker.Entities.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final userRepository repository ;
    private final PasswordEncoder passwordEncoder ;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
            .nom(request.getNom())
            .prenom(request.getPrenom())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role("ROLE_USER")
            .username(request.getUsername())
            .trajets(new HashSet<>())
            .build();
    repository.save(user);
    var jwtToken=jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        var response = new AuthenticationResponse();
        response.setToken(jwtToken);
        response.setUser(user);
        return response;
    }
}
