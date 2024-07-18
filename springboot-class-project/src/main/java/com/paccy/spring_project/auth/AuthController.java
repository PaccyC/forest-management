package com.paccy.spring_project.auth;


import com.paccy.spring_project.entities.user.User;
import com.paccy.spring_project.services.AuthService;
import com.paccy.spring_project.services.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> regisster(
            @RequestBody RegisterRequest registerRequest
    ){
        AuthenticationResponse authResponse= authService.register(registerRequest);
        return ResponseEntity.ok(authResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody User authenticationRequest
    ){
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }
}
