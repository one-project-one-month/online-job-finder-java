package com.opom.jobfinder.model.entity.company;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.job.Job;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Company extends AbstractEntity {

    @Id
    @Column(name = "account_id")
    private UUID id;

    private String phone;
    private String website;
    private String address;
    private String description;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}
