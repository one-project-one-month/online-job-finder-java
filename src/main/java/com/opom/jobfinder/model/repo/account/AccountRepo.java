package com.opom.jobfinder.model.repo.account;

import com.opom.jobfinder.model.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepo extends JpaRepository<Account, UUID> {
}
