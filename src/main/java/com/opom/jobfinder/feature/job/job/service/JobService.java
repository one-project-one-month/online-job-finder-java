package com.opom.jobfinder.feature.job.job.service;

import com.opom.jobfinder.feature.job.job.dto.JobDto;

import java.util.List;
import java.util.UUID;

public interface JobService {
    List<JobDto> listAllJobs();
    JobDto getJobById(UUID id);
    JobDto createJob(JobDto jobDto);
    JobDto updateJob(JobDto jobDto, UUID id);
    boolean deleteJob(UUID id);
}
