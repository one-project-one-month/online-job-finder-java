package com.opom.jobfinder.feature.company.company.service;

import com.opom.jobfinder.feature.company.company.dto.CompanyDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    CompanyDto getCompanyById(UUID id);
    List<CompanyDto> getAllCompanies();
}
