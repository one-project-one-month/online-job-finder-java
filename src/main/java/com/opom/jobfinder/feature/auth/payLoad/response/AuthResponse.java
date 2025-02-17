package com.opom.jobfinder.feature.auth.payLoad.response;

import java.util.Date;

public record AuthResponse  (
        String token,
        Date expiredAt,
        String refreshToken
    )
{
    public static AuthResponse of (String token, Date expiredAt, String refreshToken) {
        return new AuthResponse(token, expiredAt, refreshToken);
    }
}
