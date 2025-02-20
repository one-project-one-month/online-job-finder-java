package com.opom.jobfinder.feature.applicant.service.impl;

import org.springframework.stereotype.Service;

import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;
import com.opom.jobfinder.feature.applicant.mapper.ApplicantMapper;
import com.opom.jobfinder.feature.applicant.service.ApplicantService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.repo.applicant.ApplicantRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ApplicantServiceImpl implements ApplicantService{

        private final ApplicantRepo applicantRepo;
        private final ApplicantMapper applicantMapper;

        public Applicant UpdateApplicantProfileDto(String userId ,@Valid UpdateApplicantProfileDto dto){
                Applicant applicantProfile = applicantMapper.toApplicant(dto);  
                Applicant updatedProfile = applicantRepo.save(applicantProfile);
                return updatedProfile;
        }

}
