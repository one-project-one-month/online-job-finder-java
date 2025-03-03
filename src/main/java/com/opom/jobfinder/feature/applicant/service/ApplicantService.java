package com.opom.jobfinder.feature.applicant.service;

import com.opom.jobfinder.model.entity.applicant.Applicant;

import java.util.UUID;

public interface ApplicantService {
    Applicant findById(UUID id);
}
