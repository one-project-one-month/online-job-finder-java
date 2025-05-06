package com.opom.jobfinder.feature.admin.account.service.impl;

import com.opom.jobfinder.feature.admin.account.service.AccountService;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.repo.account.AccountRepo;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    @Override
    public Account updateAccount(UUID id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new BadRequestException(Translator.toLocale(MessageConstants.USER_DOES_NOT_EXIST,id)));
        account.setStatus(!account.isStatus());
        account = accountRepo.save(account);
        return account;
    }
}
