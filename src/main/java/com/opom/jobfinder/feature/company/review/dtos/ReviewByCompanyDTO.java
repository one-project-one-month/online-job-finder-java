package com.opom.jobfinder.feature.company.review.dtos;

import java.util.UUID;

public record ReviewByCompanyDTO(
        String comment,
        Double rating,
        CompanyDTO company,
        ApplicantDTO applicant) {
    public record CompanyDTO(
            UUID id,
            String name
    ){}
    public record ApplicantDTO(
            UUID id,
            String fullname
    ) {}
}
