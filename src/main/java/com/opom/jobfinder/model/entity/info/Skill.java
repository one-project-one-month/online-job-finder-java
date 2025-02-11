package com.opom.jobfinder.model.entity.info;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;

public class Skill extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String description;
}
