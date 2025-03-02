package com.opom.jobfinder.model.entity.job;

import java.util.List;
import java.util.UUID;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.info.JobCategory;
import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.entity.info.Skill;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Job extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private int post;
    private String requirement;
    private Double salary;
    private String address;

    private Type type;
    private Status jobStatus;

    @ManyToOne
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    private JobCategory jobCategory;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "job_skill")
    private List<Skill> skills;

    @OneToMany(mappedBy = "job")
    private List<JobApplication> jobApplications;
        
    public enum Type {
        Remote, OnSite, Hybrid
    }

    public enum  Status {
        Open, Close
    }
    
}
