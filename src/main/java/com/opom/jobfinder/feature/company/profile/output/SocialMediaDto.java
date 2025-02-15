package com.opom.jobfinder.feature.company.profile.output;

import com.opom.jobfinder.model.entity.account.SocialMedia;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialMediaDto {
    private int id;
    private String link;

    public static SocialMediaDto from(SocialMedia socialMedia) {
        return new SocialMediaDto(socialMedia.getId(), socialMedia.getLink());
    }
}
