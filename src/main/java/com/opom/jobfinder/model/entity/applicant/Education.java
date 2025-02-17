package com.opom.jobfinder.model.entity.applicant;

import java.time.LocalDate;

import com.opom.jobfinder.model.entity.AbstractEntity;

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
public class Education extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String school;
    private String degree;
    private String field;
    private String description;
    private boolean attending;

    private LocalDate startAt;
    private LocalDate endAt;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
