package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.JobApplication;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Applicant extends AbstractEntity {

    @Id
    @Column(name = "account_id")
    private UUID id;
    private String phone;
    private String address;
    private String description;
    private int currentResumeId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

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
    
    @OneToMany(mappedBy = "applicant")
    private List<ApplicantJobCategory> applicantJobCategories;
}
