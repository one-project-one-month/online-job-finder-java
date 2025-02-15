package com.opom.jobfinder.feature.company.profile.controller;

import com.opom.jobfinder.feature.company.profile.service.CompanyProfileService;
import com.opom.jobfinder.utility.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
