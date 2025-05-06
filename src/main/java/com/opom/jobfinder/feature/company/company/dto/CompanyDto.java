package com.opom.jobfinder.feature.company.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CompanyDto {
    private UUID id;
    private String phone;
    private String website;
    private String address;
    private String description;
}
