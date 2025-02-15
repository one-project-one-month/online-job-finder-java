package com.opom.jobfinder.feature.company.profile.output;

import com.opom.jobfinder.model.entity.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfile {
    private UUID id;
    private String name;
    private String email;
    private String profilePhoto;
    private String role;
    private String phone;
    private String website;
    private String address;
    private String description;
    private List<SocialMediaDto> socialMedias;
    private boolean completed;

    public static CompanyProfile from(Company company) {
        return CompanyProfile.builder()
                .id(company.getId())
                .name(company.getAccount().getName())
                .email(company.getAccount().getEmail())
                .profilePhoto(company.getAccount().getProfilePhoto())
                .role(company.getAccount().getRole().getName())
                .phone(company.getPhone())
                .website(company.getWebsite())
                .address(company.getAddress())
                .description(company.getDescription())
                .socialMedias(company.getAccount().getSocialMedias().stream().map(SocialMediaDto::from).toList())
                .completed(company.getAccount().isCompleted())
                .build();
    }

}
