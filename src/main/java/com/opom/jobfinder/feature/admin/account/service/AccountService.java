package com.opom.jobfinder.feature.admin.account.service;

import com.opom.jobfinder.model.entity.account.Account;

import java.util.UUID;

public interface AccountService {
    Account updateAccount(UUID id);
}
