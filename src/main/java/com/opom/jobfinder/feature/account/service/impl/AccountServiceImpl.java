package com.opom.jobfinder.feature.account.service.impl;

import com.opom.jobfinder.feature.account.service.AccountService;
import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.model.repo.account.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final AuthService authService;

    @Override
    public String upload(MultipartFile file) {
        var account = accountRepo.findById(authService.getLoginUserId()).orElseThrow();
        
        return "";
    }
}
