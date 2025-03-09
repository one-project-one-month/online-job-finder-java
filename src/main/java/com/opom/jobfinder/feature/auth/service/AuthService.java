package com.opom.jobfinder.feature.auth.service;

import com.opom.jobfinder.feature.auth.payLoad.request.AuthRequest;
import com.opom.jobfinder.feature.auth.payLoad.request.ChangePasswordRequest;
import com.opom.jobfinder.feature.auth.payLoad.request.RegisterRequest;
import com.opom.jobfinder.feature.auth.payLoad.response.AuthResponse;
import com.opom.jobfinder.model.entity.account.Account;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface AuthService {
    AuthResponse signup(RegisterRequest request);
    AuthResponse signin(AuthRequest request);
    AuthResponse refresh(HttpServletRequest request);
    UUID getLoginUserId();
    Account getLoginUserAccount();
    AuthResponse changePassword(ChangePasswordRequest request);
}
