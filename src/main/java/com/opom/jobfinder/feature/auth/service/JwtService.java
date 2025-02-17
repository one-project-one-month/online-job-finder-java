package com.opom.jobfinder.feature.auth.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {
    String extractEmail(String token);
    String generateToken(UserDetails userDetails, Long jwtExpiration);
    String generateRefreshToken(UserDetails userDetails, Long refreshExpiration);
    boolean isTokenValid(String token, UserDetails userDetails);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    <T> T extractClaim(String token, String claimKey, Class<T> claimType);
}
