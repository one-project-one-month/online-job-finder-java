package com.opom.jobfinder.feature.applicant.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opom.jobfinder.config.AppConfig;
import com.opom.jobfinder.feature.applicant.dto.ApplicantResponse;
import com.opom.jobfinder.feature.applicant.dto.UpdateApplicantProfileDto;
import com.opom.jobfinder.feature.applicant.service.ApplicantService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.services.ImageUploadService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("applicants")
public class ApplicantController {
        
        private ApplicantService applicantService;
        private ImageUploadService imageUploadService;

       @PutMapping("profile")
       public ResponseEntity<BaseResponse> updateApplicantProfile(@Valid @RequestBody UpdateApplicantProfileDto dto ){
                ApplicantResponse updatedProfile = applicantService.updateApplicantProfile("549d37a957f74352ba15ff2cd3df08e3",dto);
                return ResponseEntity.status(201).body(BaseResponse.of(null, updatedProfile, "Successfully updated profile"));
        }

        @PatchMapping(value = "upload-resume",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public String updateApplicantProfileResume(@Valid @RequestParam("file") MultipartFile file){
                try {
                 String uploadedFilePath = imageUploadService.saveImage(file);
                 // ! --- create resume ----
                return uploadedFilePath;
                } catch (Exception e) {
                     throw new RuntimeException(e.getMessage());
                }
        }

}
