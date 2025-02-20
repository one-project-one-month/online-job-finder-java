package com.opom.jobfinder.feature.applicant.service;


import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;
import com.opom.jobfinder.model.entity.applicant.Applicant;

public interface ApplicantService {
        public Applicant UpdateApplicantProfileDto(String userId , UpdateApplicantProfileDto dto);
}
