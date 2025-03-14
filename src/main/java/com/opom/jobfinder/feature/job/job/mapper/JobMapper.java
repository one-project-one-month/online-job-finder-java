package com.opom.jobfinder.feature.job.job.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.job.job.dto.JobDto;
import com.opom.jobfinder.model.entity.job.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobMapper {
    private final ObjectMapper objectMapper;

    public Job toJob(Object data) {
        try {
            return objectMapper.convertValue(data, Job.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Could not convert " + data + " to Job", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during mapping", e);
        }
    }

    public JobDto toJobDto(Job job) {
        try {
            return objectMapper.convertValue(job, JobDto.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Could not convert " + job + " to JobDto", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during mapping", e);
        }
    }
}
