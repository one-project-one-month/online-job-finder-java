package com.opom.jobfinder.feature.company.profile.controller;

import com.opom.jobfinder.feature.auth.service.AuthService;
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

    private final CompanyProfileService companyProfileService;
    private final AuthService authService;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> profile(@PathVariable("id") UUID id) {
        var data = companyProfileService.findCompanyProfileById(id);
        return ResponseEntity.ok(BaseResponse.success(data));
    }

    @GetMapping("me")
    ResponseEntity<BaseResponse> profile() {
        var data = companyProfileService.findCompanyProfileById(authService.getLoginUserId());
        return ResponseEntity.ok(BaseResponse.success(data));
    }

    @PutMapping("me")
    ResponseEntity<BaseResponse> update(@RequestBody CompanyProfileForm form) {
        var data = companyProfileService.updateProfile(form);
        return ResponseEntity.ok(BaseResponse.success(data));
    }

}
