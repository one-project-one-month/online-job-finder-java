package com.opom.jobfinder.feature.company.company.controller;

import com.opom.jobfinder.feature.company.company.dto.CompanyDto;
import com.opom.jobfinder.feature.company.company.service.CompanyService;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    //List All Companies
    @GetMapping
    public ResponseEntity<BaseResponse> listAllCompanies() {
        List<CompanyDto> companies = companyService.getAllCompanies();
        BaseResponse baseResponse = BaseResponse.of(MessageConstants.SUCCESS, companies, Translator.toLocale(MessageConstants.SUCCESS));
        return ResponseEntity.ok(baseResponse);
    }

    //company detail
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCompanyById(@PathVariable UUID id) {
        CompanyDto company = companyService.getCompanyById(id);
        if (company != null) {
            BaseResponse baseResponse = BaseResponse.of(MessageConstants.SUCCESS, company, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.ok(baseResponse);
        }
        BaseResponse baseResponse = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.ok(baseResponse);
    }
}
