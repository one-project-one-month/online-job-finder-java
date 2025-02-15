package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.applicant.pk.ApplicantJobCategoryPk;
import com.opom.jobfinder.model.entity.info.JobCategory;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ApplicantJobCategory extends AbstractEntity {

    @EmbeddedId
    private ApplicantJobCategoryPk id;

    @ManyToOne
    @JoinColumn(name = "job_category_id", insertable = false, updatable = false)
    private JobCategory jobCategory;

    @ManyToOne
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private Applicant applicant;

}
