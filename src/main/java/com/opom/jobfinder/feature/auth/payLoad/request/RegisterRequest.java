package com.opom.jobfinder.feature.auth.payLoad.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest (
    @NotNull @NotBlank String name,
    @Email String email,
    @NotNull @NotBlank @Length(min = 4) String password,
    @NotNull @Min(1) int role
) {}
