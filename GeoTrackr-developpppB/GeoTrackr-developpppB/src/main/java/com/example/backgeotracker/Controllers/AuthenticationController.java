package com.example.backgeotracker.Controllers;

import com.example.backgeotracker.Auth.AuthenticationRequest;
import com.example.backgeotracker.Auth.AuthenticationResponse;
import com.example.backgeotracker.Auth.RegisterRequest;
import com.example.backgeotracker.config.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service ;
    @PostMapping("/Register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody AuthenticationRequest request ){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
