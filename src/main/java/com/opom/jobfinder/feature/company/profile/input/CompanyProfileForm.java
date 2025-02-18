package com.opom.jobfinder.feature.company.profile.input;

import com.opom.jobfinder.model.entity.company.Company;
import lombok.Data;

@Data
public class CompanyProfileForm {
    private String name;
    private String phone;
    private String website;
    private String address;
    private String description;
    private int locationId;
}
