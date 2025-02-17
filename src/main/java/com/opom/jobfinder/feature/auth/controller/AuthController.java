package com.opom.jobfinder.feature.auth.controller;

import com.opom.jobfinder.feature.auth.payLoad.request.AuthRequest;
import com.opom.jobfinder.feature.auth.payLoad.request.RegisterRequest;
import com.opom.jobfinder.feature.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<?> signup(@Validated @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    ResponseEntity<?> signin(@Validated @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }

    @GetMapping("/refresh")
    ResponseEntity<?> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }
}
