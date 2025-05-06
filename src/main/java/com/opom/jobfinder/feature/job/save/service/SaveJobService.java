package com.opom.jobfinder.feature.job.save.service;

import com.opom.jobfinder.model.entity.applicant.SavedJob;


import java.util.List;
import java.util.UUID;

public interface SaveJobService {
    String save(UUID jobId);
    List<SavedJob> getSaveJobsByApplicant();
    List<SavedJob> un_save(UUID jobId);

}
