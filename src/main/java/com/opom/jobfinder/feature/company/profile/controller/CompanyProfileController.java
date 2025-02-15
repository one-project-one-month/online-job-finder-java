package com.opom.jobfinder.feature.company.profile.controller;

import com.opom.jobfinder.feature.company.profile.input.CompanyProfileForm;
import com.opom.jobfinder.feature.company.profile.service.CompanyProfileService;
import com.opom.jobfinder.utility.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("recruiter/me")
public class CompanyProfileController {

    private final CompanyProfileService service;

    @GetMapping
    ResponseEntity<BaseResponse> profile() {
        var data = service.findCompanyProfileByEmail(null);
        return ResponseEntity.ok(BaseResponse.of(null, data, "Company profile"));
    }

    @PutMapping
    ResponseEntity<BaseResponse> update(@Validated @RequestBody CompanyProfileForm form) {
        var data = service.updateProfile(form);
        return ResponseEntity.ok(BaseResponse.of(null, data, "Updated company profile"));
    }

}
