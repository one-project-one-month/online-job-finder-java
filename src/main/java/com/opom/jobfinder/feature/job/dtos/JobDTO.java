package com.opom.jobfinder.feature.job.dtos;

import com.opom.jobfinder.model.entity.job.Job;

public record JobDTO (
        String title,
        String description,
        int post,
        String address,
        Job.Type type,
        String status
){
}
