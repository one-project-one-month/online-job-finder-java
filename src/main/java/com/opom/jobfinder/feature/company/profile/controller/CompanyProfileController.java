package com.opom.jobfinder.feature.company.profile.controller;

import com.opom.jobfinder.feature.company.profile.input.CompanyProfileForm;
import com.opom.jobfinder.feature.company.profile.service.CompanyProfileService;
import com.opom.jobfinder.utility.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("recruiter")
public class CompanyProfileController {

    private final CompanyProfileService service;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> profile(@PathVariable("id") UUID id) {
        var data = service.findCompanyProfileById(id);
        return ResponseEntity.ok(BaseResponse.of(null, data, "Company profile"));
    }

    @GetMapping("me")
    ResponseEntity<BaseResponse> profile() {
        var data = service.findCompanyProfileByEmail(null);
        return ResponseEntity.ok(BaseResponse.of(null, data, "Company profile"));
    }

    @PutMapping
    ResponseEntity<BaseResponse> update(@RequestBody CompanyProfileForm form) {
        var data = service.updateProfile(form);
        return ResponseEntity.ok(BaseResponse.of(null, data, "Updated company profile"));
    }

}
