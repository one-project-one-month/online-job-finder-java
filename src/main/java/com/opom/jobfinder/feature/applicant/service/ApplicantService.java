package com.opom.jobfinder.feature.applicant.service;

import com.opom.jobfinder.feature.applicant.dto.ApplicantResponse;
import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;

public interface ApplicantService {
        public ApplicantResponse updateApplicantProfile(String userId , UpdateApplicantProfileDto dto);
}
