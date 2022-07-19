package com.example.springwebtraining.controller;

import com.example.springwebtraining.controller.dto.request.LoginDto;
import com.example.springwebtraining.error.ApiException;
import com.example.springwebtraining.error.ErrorCode;
import com.example.springwebtraining.model.DUser;
import com.example.springwebtraining.repository.DUserRepository;
import com.example.springwebtraining.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private DUserRepository userRepository;
    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public AuthController(DUserRepository userRepository, JWTUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity registerHandler(@RequestBody DUser requestBody) {
        String encodedPass = passwordEncoder.encode(requestBody.getPassword());
        requestBody.setPassword(encodedPass);
        requestBody = userRepository.save(requestBody);
        String token = jwtUtil.generateToken(requestBody.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity loginHandler(@RequestBody LoginDto requestBody) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(requestBody.email(), requestBody.password());

            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(requestBody.email());

            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (AuthenticationException authExc) {
            throw new ApiException(ErrorCode.UNAUTHORIZED);
        }
    }

}
