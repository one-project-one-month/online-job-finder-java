package com.opom.jobfinder.feature.auth.payLoad.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequest (
        @Email String email,
        @NotBlank @NotNull String password
) {}
