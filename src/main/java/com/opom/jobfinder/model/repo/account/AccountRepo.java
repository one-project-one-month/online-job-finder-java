package com.opom.jobfinder.model.repo.account;

import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.repo.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends BaseRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
}
