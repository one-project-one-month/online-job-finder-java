package com.opom.jobfinder.feature.job.save.service.impl;

import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.feature.job.save.service.SaveJobService;
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

@Service
@AllArgsConstructor
public class SaveJobServiceImpl implements SaveJobService {

    private final SaveJobRepo saveJobRepo;
    private final JobRepo jobRepo;
    private final AuthService authService;
    private final ApplicantRepo applicantRepo;

    @Override
    public SavedJob save(UUID jobId) {
        SavedJobPk savedJobPk = getSavedJobPk(jobId);
        if(savedJobPk != null) {
            Optional<SavedJob> savedJob = saveJobRepo.findById(savedJobPk);
            if(savedJob.isPresent()) {
                throw new IllegalArgumentException("Job Already Saved");
            }else {
                SavedJob savedJob1 = new SavedJob();
                savedJob1.setId(savedJobPk);
                return saveJobRepo.save(savedJob1);
            }
        }else {
            throw new BadRequestException("Job or Applicant Not Found!");
        }
    }

    @Override
    public List<SavedJob> getSaveJobsByApplicant() {
        UUID applicantId = authService.getLoginUserId();
        Optional<Applicant> applicant = applicantRepo.findById(applicantId);
        if(applicant.isPresent()) {
            return saveJobRepo.findByApplicantOrderByCreatedAtDesc(applicant.get());
        }else {
            throw new BadRequestException("Applicant Not Found!");
        }
    }

    @Override
    public List<SavedJob> un_save(UUID jobId) {
        SavedJobPk savedJobPk = getSavedJobPk(jobId);
        if(savedJobPk != null) {
            Optional<SavedJob> savedJob = saveJobRepo.findById(savedJobPk);
            if(savedJob.isPresent()) {
                saveJobRepo.deleteById(savedJobPk);
                return saveJobRepo.findByApplicantOrderByCreatedAtDesc(applicant.get());
            }else {
                throw new IllegalArgumentException("Job Already Saved");
            }
        }else {
            throw new BadRequestException("Job Not Found!");
        }
    }

    private SavedJobPk getSavedJobPk(UUID jobId) {
        UUID applicantID = authService.getLoginUserId();
        Optional<Job> job = jobRepo.findById(jobId);
        Optional<Applicant> applicant = applicantRepo.findById(applicantID);

        if(job.isPresent() && applicant.isPresent()) {
            return new SavedJobPk(job.get().getId(),applicant.get().getId());
        } else {
            return null;
        }
    }
}
