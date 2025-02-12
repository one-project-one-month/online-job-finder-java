package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.entity.job.JobApplication;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Applicant extends AbstractEntity {

    @Id
    @Column(name = "account_id")
    private UUID id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ApplicantInfo info;

    @OneToMany(mappedBy = "applicant")
    private List<Resume> resumes;

    @OneToMany(mappedBy = "applicant")
    private List<Review> reviews;

    @OneToMany(mappedBy = "applicant")
    private List<SavedJob> savedJobs;

    @OneToMany(mappedBy = "applicant")
    private List<Education> educations;

    @OneToMany(mappedBy = "applicant")
    private List<Experience> experiences;

    @OneToMany(mappedBy = "applicant")
    private List<JobApplication> jobApplications;
}
