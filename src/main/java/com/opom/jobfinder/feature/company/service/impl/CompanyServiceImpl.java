package com.opom.jobfinder.feature.company.service.impl;

import com.opom.jobfinder.feature.company.output.CompanyDetail;
import com.opom.jobfinder.feature.company.output.CompanyInfo;
import com.opom.jobfinder.feature.company.output.CompanyJobDto;
import com.opom.jobfinder.feature.company.service.CompanyService;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;

    @Override
    public List<CompanyInfo> findAll() {
        return companyRepo.findAll().stream()
                .map(CompanyInfo::from)
                .toList();
    }

    @Override
    public Optional<CompanyDetail> findById(UUID id) {
        return companyRepo.findById(id).map(CompanyDetail::from);
    }

    @Override
    public List<CompanyJobDto> findJobsById(UUID id) {
        return jobRepo.findByCompanyId(id).stream()
                .map(CompanyJobDto::from)
                .toList();
    }
}
