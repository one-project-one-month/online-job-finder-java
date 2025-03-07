package com.opom.jobfinder.feature.job.save.service;

import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.applicant.SavedJob;
import com.opom.jobfinder.model.entity.applicant.pk.SavedJobPk;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.applicant.ApplicantRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.model.repo.job.SaveJobRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SaveJobService {
    SavedJob save(UUID jobId);
    List<SavedJob> getSaveJobsByApplicant();
    List<SavedJob> un_save(UUID jobId);

}
