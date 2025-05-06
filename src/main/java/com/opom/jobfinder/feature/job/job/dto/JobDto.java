package com.opom.jobfinder.feature.job.job.dto;

import com.opom.jobfinder.model.entity.job.Job.Status;
import com.opom.jobfinder.model.entity.job.Job.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class JobDto {
    private UUID id;
    private String title;
    private String description;
    private int post;
    private String requirement;
    private Double salary;
    private String address;
    private Type type;
    private Status JobStatus;
}
