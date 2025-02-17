package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.applicant.pk.SavedJobPk;
import com.opom.jobfinder.model.entity.job.Job;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SavedJob extends AbstractEntity {

    @EmbeddedId
    private SavedJobPk id;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private Applicant applicant;
}
