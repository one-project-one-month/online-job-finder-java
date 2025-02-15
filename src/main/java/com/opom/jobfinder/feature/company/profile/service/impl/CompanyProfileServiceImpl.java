package com.opom.jobfinder.feature.company.profile.service.impl;

import com.opom.jobfinder.feature.company.profile.input.CompanyProfileForm;
import com.opom.jobfinder.feature.company.profile.output.CompanyProfile;
import com.opom.jobfinder.feature.company.profile.service.CompanyProfileService;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyRepo companyRepo;

    @Override
    public CompanyProfile findCompanyProfileByEmail(String email) {
        return companyRepo.findOneByEmail(email)
                .map(CompanyProfile::from)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    }

    @Override
    public CompanyProfile updateProfile(CompanyProfileForm form) {
        // need to use login username for the email argument. waiting for security config
        var company = companyRepo.findOneByEmail(null).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        update(company, form);
        companyRepo.saveAndFlush(company);
        checkProfileCompletion(company);
        nextVersion(company);
        return CompanyProfile.from(company);
    }

    private void nextVersion(Company company) {
        company.setVersion(company.getVersion() + 1);
    }

    private void checkProfileCompletion(Company company) {
        if(StringUtils.hasLength(company.getAddress()) &&
            StringUtils.hasLength(company.getDescription()) &&
            StringUtils.hasLength((company.getPhone())) &&
            null != company.getLocation()) {
            company.getAccount().setCompleted(true);
        } else {
            company.getAccount().setCompleted(false);
        }
    }

    private void update(Company company, CompanyProfileForm form) {
        company.getAccount().setName(form.getName());
        company.getAccount().setProfilePhoto(form.getProfilePhoto());
        company.setPhone(form.getPhone());
        company.setWebsite(form.getWebsite());
        company.setAddress(form.getAddress());
        company.setDescription(form.getDescription());
        // need to update location. waiting for location repo
    }

}
