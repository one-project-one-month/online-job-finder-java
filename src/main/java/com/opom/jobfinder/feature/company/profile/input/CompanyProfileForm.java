package com.opom.jobfinder.feature.company.profile.input;

import com.opom.jobfinder.feature.company.profile.output.SocialMediaDto;
import lombok.Data;

import java.util.List;

@Data
public class CompanyProfileForm {
    private String name;
    private String profilePhoto;
    private String phone;
    private String website;
    private String address;
    private String description;
    private List<SocialMediaDto> socialMedias;
}
