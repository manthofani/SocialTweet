package com.backend.socialtweet.controller;

import com.backend.socialtweet.dto.TokenDTO;
import com.backend.socialtweet.dto.UserDto;
import com.backend.socialtweet.request.AuthRequest;
import com.backend.socialtweet.request.RegisterRequest;
import com.backend.socialtweet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenDTO> loginRequest(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUpRequest(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }
}
