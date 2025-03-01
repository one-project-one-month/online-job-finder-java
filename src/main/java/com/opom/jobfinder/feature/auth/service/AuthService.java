package com.opom.jobfinder.feature.auth.service;

import com.opom.jobfinder.feature.auth.payLoad.request.AuthRequest;
import com.opom.jobfinder.feature.auth.payLoad.request.RegisterRequest;
import com.opom.jobfinder.feature.auth.payLoad.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface AuthService {
    AuthResponse signup(RegisterRequest request);
    AuthResponse signin(AuthRequest request);
    AuthResponse refresh(HttpServletRequest request);
    UUID getLoginUserId();
}
