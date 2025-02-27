package com.opom.jobfinder.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.model.repo.account.AccountRepo;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import com.opom.jobfinder.utility.exception.UnauthorizedException;
import com.opom.jobfinder.utility.exception.UnexpectedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static com.opom.jobfinder.utility.MessageConstants.USER_DOES_NOT_EXIST;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig {
    private final AccountRepo accountRepo;

    @Bean
    public ObjectMapper objectMapper (){
        return new ObjectMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> accountRepo.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(Translator.toLocale(USER_DOES_NOT_EXIST, email)));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Value("${ojf.allowedUrls}")
    private String[] allowedUrls;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.info("Allowed Urls : {}", Arrays.toString(allowedUrls));
        if (allowedUrls == null || allowedUrls.length == 0) {
            throw new UnexpectedException("There is no allowed frontend urls.");
        }

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(allowedUrls));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
