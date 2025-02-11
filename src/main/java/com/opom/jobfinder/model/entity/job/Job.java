package com.opom.jobfinder.model.entity.job;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.company.Company;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Job extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private int post;
    private String requirements;
    private Double salary;
    private LocalDate startAt;
    private LocalDate dueAt;
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;
}
