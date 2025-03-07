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
        Optional<Job> job = jobRepo.findById(jobId);
        if(job.isPresent()) {
            UUID applicantID = authService.getLoginUserId();
            Optional<Applicant> applicant = applicantRepo.findById(applicantID);
            SavedJobPk savedJobPk = new SavedJobPk(applicant.get().getId(), job.get().getId());
            SavedJob savedJob = new SavedJob();

            savedJob.setId(savedJobPk);
            return saveJobRepo.save(savedJob);
        }else {
            throw new BadRequestException("Job Not Found!");
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
        Optional<Job> job = jobRepo.findById(jobId);
        if(job.isPresent()) {
            UUID applicantID = authService.getLoginUserId();
            Optional<Applicant> applicant = applicantRepo.findById(applicantID);
            SavedJobPk savedJobPk = new SavedJobPk(applicant.get().getId(), job.get().getId());

            saveJobRepo.deleteById(savedJobPk);
            return saveJobRepo.findByApplicantOrderByCreatedAtDesc(applicant.get());
        }else {
            throw new BadRequestException("Job Not Found!");
        }
    }
}
