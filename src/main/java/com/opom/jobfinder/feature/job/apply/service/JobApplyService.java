package com.opom.jobfinder.feature.job.apply.service;

import com.opom.jobfinder.model.entity.job.JobApplication;

import java.util.UUID;

public interface JobApplyService {
    JobApplication applyJob (UUID jobId, Integer resumeId);
}
