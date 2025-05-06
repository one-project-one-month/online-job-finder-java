package com.opom.jobfinder.feature.company.controller;

import com.opom.jobfinder.feature.company.service.CompanyService;
import com.opom.jobfinder.utility.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("recruiters")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    ResponseEntity<BaseResponse> list() {
        var list = companyService.findAll();
        return ResponseEntity.ok(BaseResponse.success(list));
    }

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> detail(@PathVariable UUID id) {
        var data = companyService.findById(id).orElseThrow();
        return ResponseEntity.ok(BaseResponse.success(data));
    }

    @GetMapping("{id}/jobs")
    ResponseEntity<BaseResponse> jobs(@PathVariable UUID id) {
        var list = companyService.findJobsById(id);
        return ResponseEntity.ok(BaseResponse.success(list));
    }
}