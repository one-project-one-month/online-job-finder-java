package com.opom.jobfinder.feature.admin.location.dtos;

import java.util.UUID;

public record GetCompanyByLocation(
        String companyName,
        String address,
        String description,
        UUID locationId,
        double avgReview
) {}