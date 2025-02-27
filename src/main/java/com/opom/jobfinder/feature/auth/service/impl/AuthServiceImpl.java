package com.opom.jobfinder.feature.auth.service.impl;

import com.opom.jobfinder.config.TokenType;
import com.opom.jobfinder.feature.auth.payLoad.request.AuthRequest;
import com.opom.jobfinder.feature.auth.payLoad.request.RegisterRequest;
import com.opom.jobfinder.feature.auth.payLoad.response.AuthResponse;
import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.feature.auth.service.JwtService;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.account.Role;
import com.opom.jobfinder.model.repo.account.AccountRepo;
import com.opom.jobfinder.model.repo.account.RoleRepo;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.AccessDeniedException;
import com.opom.jobfinder.utility.exception.BadRequestException;
import com.opom.jobfinder.utility.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.opom.jobfinder.utility.MessageConstants.USER_DOES_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Value("${jwt.jwtExpiration}")
    private Long jwtExpiration;

    @Value("${jwt.refreshExpiration}")
    private Long refreshExpiration;

    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepo roleRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthResponse signup(RegisterRequest request) {
        Optional<Role> roleOptional = roleRepo.findById(request.role());
        Role role = roleOptional.orElseThrow(() -> new BadRequestException("Role id does not exist."));

        if(role.getName().contains("ADMIN")) throw new AccessDeniedException(Translator.toLocale(MessageConstants.ACCESS_DENIED_ERROR));

        Account account = Account.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(role)
                .build();
        accountRepo.save(account);
        return getAuthResponse(account);
    }

    @Override
    public AuthResponse refresh(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException(Translator.toLocale(MessageConstants.UNAUTHORIZED_ERROR));
        }
        refreshToken = authHeader.substring(7);

        userEmail = jwtService.extractClaim(refreshToken, Claims::getSubject);
        TokenType tokenType = jwtService.extractClaim(refreshToken, "tokenType", TokenType.class);
        if (userEmail != null && tokenType.equals(TokenType.REFRESH_TOKEN)) {
            Account account = accountRepo.findByEmail(userEmail)
                    .orElseThrow(() -> new BadRequestException(Translator.toLocale(USER_DOES_NOT_EXIST, userEmail)));
            if (jwtService.isTokenValid(refreshToken, account)) {
                return getAuthResponse(account);
            }
        }
        throw new UnauthorizedException(Translator.toLocale(MessageConstants.UNAUTHORIZED_ERROR));
    }

    @Override
    public UUID getLoginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Account) {
                return ((Account) principal).getId();
            }
        }
        return null;
    }

    @Override
    public AuthResponse signin(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Account account = accountRepo.findByEmail(request.email())
                .orElseThrow(() -> new BadRequestException(Translator.toLocale(USER_DOES_NOT_EXIST, request.email())));
        return getAuthResponse(account);
    }

    private AuthResponse getAuthResponse(Account account) {
        String jwtToken = jwtService.generateToken(account, jwtExpiration);
        String refreshToken = jwtService.generateRefreshToken(account, refreshExpiration);
        Date expiredAt = new Date(System.currentTimeMillis() + jwtExpiration);
        return AuthResponse.of(jwtToken, expiredAt, refreshToken);
    }
}
