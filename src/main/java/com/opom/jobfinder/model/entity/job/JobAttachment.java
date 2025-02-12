package com.opom.jobfinder.model.entity.job;

import com.opom.jobfinder.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class JobAttachment extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private Job job;
}
