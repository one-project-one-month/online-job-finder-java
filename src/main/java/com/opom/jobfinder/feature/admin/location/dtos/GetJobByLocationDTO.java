package com.opom.jobfinder.feature.admin.location.dtos;


import com.opom.jobfinder.model.entity.job.Job.Type;
import jakarta.validation.constraints.NotNull;

public record GetJobByLocationDTO(
        String title,
        String description,
        int post,
        String address,
        Type type,
        String status
) {}
