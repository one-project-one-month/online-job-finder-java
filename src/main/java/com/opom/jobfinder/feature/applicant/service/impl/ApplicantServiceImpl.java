package com.opom.jobfinder.feature.applicant.service.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.applicant.dto.ApplicantResponse;
import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;
import com.opom.jobfinder.feature.applicant.mapper.ApplicantMapper;
import com.opom.jobfinder.feature.applicant.service.ApplicantService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.repo.applicant.ApplicantRepo;
import com.opom.jobfinder.model.repo.location.LocationRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import com.opom.jobfinder.model.entity.info.Location;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ApplicantServiceImpl  implements ApplicantService{

        private final ApplicantMapper applicantMapper;
        private final LocationRepo locationRepo;
        private final ObjectMapper objectmapper;
        private final ApplicantRepo applicantRepo;

        @Override
        public ApplicantResponse updateApplicantProfile(String userId , UpdateApplicantProfileDto dto){
                // ! --- validation for resume , jobs && set to applicant profile ---
                Location foundLocation = locationRepo.findById(dto.location_id()).orElseThrow(()-> new BadRequestException("location not found"));
                var applicantProfile = applicantMapper.toApplicant(dto); 
                applicantProfile.setLocation(foundLocation);
                ApplicantResponse savedApplicant = objectmapper.convertValue(applicantRepo.save(applicantProfile), ApplicantResponse.class);
                return savedApplicant;
        }

}
