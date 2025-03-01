package com.opom.jobfinder.feature.admin.location.dtos;

import java.util.UUID;

public record GetCompanyByLocationDTO(
        String companyName,
        String address,
        String description,
        int locationId,
        double avgReview
) {}