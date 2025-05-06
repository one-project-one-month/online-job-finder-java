package com.opom.jobfinder.feature.company.output;

import com.opom.jobfinder.model.entity.company.Company;

import java.util.UUID;

public record CompanyInfo(
        UUID id,
        String name,
        String profilePhoto
) {
    public static CompanyInfo from(Company company) {
        return new CompanyInfo(
                company.getId(),
                company.getAccount().getName(),
                company.getAccount().getProfilePhoto());
    }
}
