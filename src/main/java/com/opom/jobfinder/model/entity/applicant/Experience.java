package com.opom.jobfinder.model.entity.applicant;

import java.time.LocalDate;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.info.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Experience extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;
    private String companyName;
    private String description;
    private String address;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private String jobType;
    private LocalDate startAt;
    private LocalDate endAt;
    private boolean working;

    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
