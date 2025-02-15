package com.opom.jobfinder.feature.company.profile.service.impl;

import com.opom.jobfinder.feature.company.profile.output.CompanyProfile;
import com.opom.jobfinder.feature.company.profile.output.SocialMediaDto;
import com.opom.jobfinder.feature.company.profile.service.CompanyProfileService;
import com.opom.jobfinder.model.entity.account.Role;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyRepo companyRepo;

    @Override
    public CompanyProfile findCompanyProfileByEmail(String email) {
        return companyRepo.findOneByEmail(email)
                .map(CompanyProfile::from)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    }
}
