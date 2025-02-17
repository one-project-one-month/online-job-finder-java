package com.opom.jobfinder.feature.auth.service;

import com.opom.jobfinder.feature.auth.payLoad.request.AuthRequest;
import com.opom.jobfinder.feature.auth.payLoad.request.RegisterRequest;
import com.opom.jobfinder.utility.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    BaseResponse signup(RegisterRequest request);
    BaseResponse signin(AuthRequest request);
    BaseResponse refresh(HttpServletRequest request);
}
