package com.opom.jobfinder.model.entity.company;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.job.Job;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Company extends AbstractEntity {

    @Id
    @Column(name = "account_id")
    private UUID id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @OneToOne
    @PrimaryKeyJoinColumn
    private CompanyInfo info;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}
