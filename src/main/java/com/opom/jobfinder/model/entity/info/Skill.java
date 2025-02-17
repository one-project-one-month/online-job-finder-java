package com.opom.jobfinder.model.entity.info;

import com.opom.jobfinder.model.entity.AbstractEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "skills")
@NoArgsConstructor
public class Skill extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;

    private String description;

}
