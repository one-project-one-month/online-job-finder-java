package com.opom.jobfinder.feature.job.service;

import com.opom.jobfinder.feature.job.dtos.JobDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    List<JobDTO> getJobs(String type, String location, String q);
}
