package com.opom.jobfinder.feature.company.company.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.company.company.dto.CompanyDto;
import com.opom.jobfinder.model.entity.company.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    private final ObjectMapper objectMapper;

    public Company toCompany(Object data){
        try {
            return objectMapper.convertValue(data, Company.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to map data to company", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during mapping", e);
        }
    }

    public CompanyDto toCompanyDto(Company company) {
        try {
            return objectMapper.convertValue(company, CompanyDto.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to map data to CompanyDto", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during mapping", e);
        }
    }

}
