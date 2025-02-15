package com.opom.jobfinder.feature.company.profile.service;

import com.opom.jobfinder.feature.company.profile.input.CompanyProfileForm;
import com.opom.jobfinder.feature.company.profile.output.CompanyProfile;

public interface CompanyProfileService {
    CompanyProfile findCompanyProfileByEmail(String email);

    CompanyProfile updateProfile(CompanyProfileForm form);
}
