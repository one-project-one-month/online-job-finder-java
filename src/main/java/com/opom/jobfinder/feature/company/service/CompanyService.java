package com.opom.jobfinder.feature.company.service;

import com.opom.jobfinder.feature.company.output.CompanyDetail;
import com.opom.jobfinder.feature.company.output.CompanyInfo;
import com.opom.jobfinder.feature.company.output.CompanyJobDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyService {
    List<CompanyInfo> findAll();

    Optional<CompanyDetail> findById(UUID id);

    List<CompanyJobDto> findJobsById(UUID id);
}
