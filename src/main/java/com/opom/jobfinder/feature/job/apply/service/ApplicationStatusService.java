package com.opom.jobfinder.feature.job.apply.service;

import com.opom.jobfinder.model.entity.job.JobApplication;

public interface ApplicationStatusService {
    JobApplication approveOrRejectApplication (String id, JobApplication.Status status);
    JobApplication seenApplication (String id);
}