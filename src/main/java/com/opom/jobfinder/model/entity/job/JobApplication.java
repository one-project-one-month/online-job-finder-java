package com.opom.jobfinder.model.entity.job;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.applicant.Resume;
import com.opom.jobfinder.model.entity.job.pk.JobApplicationPk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class JobApplication extends AbstractEntity {

    @Id
    private JobApplicationPk id;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private Applicant applicant;

    @ManyToOne
    private Resume resume;

    public enum Status {
        Pending, Seen, Accepted, Rejected
    }
}
