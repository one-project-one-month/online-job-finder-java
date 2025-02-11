package com.opom.jobfinder.model.entity.applicant.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
public class ApplicantIndustryPk {

    @Column(name = "industry_id")
    private int industryId;
    @Column(name = "applicant_id")
    private UUID applicantId;
}
