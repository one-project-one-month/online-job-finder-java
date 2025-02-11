package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.applicant.pk.ApplicantIndustryPk;
import com.opom.jobfinder.model.entity.info.Industry;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ApplicantIndustry extends AbstractEntity {

    @Id
    private ApplicantIndustryPk id;

    @ManyToOne
    @JoinColumn(name = "industry_id", insertable = false, updatable = false)
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private Applicant applicant;

}
