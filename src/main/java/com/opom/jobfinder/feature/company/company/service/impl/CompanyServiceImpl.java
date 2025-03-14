package com.opom.jobfinder.feature.company.company.service.impl;

import com.opom.jobfinder.feature.company.company.dto.CompanyDto;
import com.opom.jobfinder.feature.company.company.mapper.CompanyMapper;
import com.opom.jobfinder.feature.company.company.service.CompanyService;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepo companyRepo;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDto getCompanyById(UUID id) {
        return companyRepo.findById(id)
                .map(companyMapper::toCompanyDto)
                .orElseThrow(() -> new BadRequestException("Company not found with id: " + id));
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepo.findAll().stream()
                .map(companyMapper::toCompanyDto)
                .collect(Collectors.toList());
    }
}
