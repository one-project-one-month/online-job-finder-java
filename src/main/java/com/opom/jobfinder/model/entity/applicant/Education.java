package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Education extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String school;
    private String degree;
    private String field;
    private String description;

    private LocalDate startAt;
    private LocalDate endAt;

    private boolean attending;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
