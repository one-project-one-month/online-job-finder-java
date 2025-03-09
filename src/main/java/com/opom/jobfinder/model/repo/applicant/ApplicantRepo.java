package com.opom.jobfinder.model.repo.applicant;

import java.util.Optional;
import java.util.UUID;

import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.repo.BaseRepository;

public interface ApplicantRepo extends BaseRepository<Applicant, UUID>{
    Optional<Applicant> findByAccount(Account account);
}
