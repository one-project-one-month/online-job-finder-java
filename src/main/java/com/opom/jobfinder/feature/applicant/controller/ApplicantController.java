package com.opom.jobfinder.feature.applicant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;
import com.opom.jobfinder.feature.applicant.service.impl.ApplicantServiceImpl;
import com.opom.jobfinder.model.entity.applicant.Applicant;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("applicants")
public class ApplicantController {
        
        private ApplicantServiceImpl applicantServiceImpl;

       @PutMapping("profile")
       public Applicant updateApplicantProfile(@Valid @RequestBody UpdateApplicantProfileDto dto ){
                Applicant updatedProfile = applicantServiceImpl.UpdateApplicantProfileDto("",dto);
                return updatedProfile;
        }

}
