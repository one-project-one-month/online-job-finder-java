package com.opom.jobfinder.feature.job.service.impl;

import com.opom.jobfinder.feature.job.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    @Override
    public List<JobsDTO> getJobs(String type, String location, String q) {
        return List.of();
    }
}
