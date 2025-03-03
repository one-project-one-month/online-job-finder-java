package com.opom.jobfinder.feature.applicant.service.impl;

import com.opom.jobfinder.feature.applicant.service.ApplicantService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.repo.applicant.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepo applicantRepo;

    @Override
    public Applicant findById(UUID id) {
        Optional<Applicant> applicant = applicantRepo.findById(id);
        return applicant.orElse(null);
    }
}
