package com.opom.jobfinder.feature.job.service;

import java.util.List;

public interface JobService {
    List<JobsDTO> getJobs(String type, String location,String q);
}
