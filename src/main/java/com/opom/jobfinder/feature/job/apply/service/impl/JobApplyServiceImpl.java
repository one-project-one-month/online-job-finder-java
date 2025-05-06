package com.opom.jobfinder.feature.job.apply.service.impl;

import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.feature.job.apply.service.JobApplyService;
import com.opom.jobfinder.model.entity.account.Account;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.applicant.Resume;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.entity.job.JobApplication;
import com.opom.jobfinder.model.entity.job.pk.JobApplicationPk;
import com.opom.jobfinder.model.repo.applicant.ApplicantRepo;
import com.opom.jobfinder.model.repo.applicant.ResumeRepo;
import com.opom.jobfinder.model.repo.job.JobApplicationRepo;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import com.opom.jobfinder.utility.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobApplyServiceImpl implements JobApplyService {

    private final JobRepo jobRepo;
    private final ResumeRepo resumeRepo;
    private final JobApplicationRepo jobApplicationRepo;
    private final AuthService authService;
    private final ApplicantRepo applicantRepo;
    private final EmailService emailService;

    @Override
    public JobApplication applyJob(UUID jobId, Integer resumeId) {
        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new BadRequestException("Job does not exist."));

        Resume resume = resumeRepo.findById(resumeId)
                .orElseThrow(() -> new BadRequestException("Resume does not exist."));

        Account account = authService.getLoginUserAccount();
        Applicant applicant = applicantRepo.findByAccount(account)
                .orElseThrow(() -> new BadRequestException("Applicant does not exist."));

        JobApplicationPk jobApplicationPk = new JobApplicationPk(jobId, applicant.getId());

        if (jobApplicationRepo.existsById(jobApplicationPk)) {
            throw new BadRequestException("Already applied to this job.");
        }

        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(jobApplicationPk);
        jobApplication.setJob(job);
        jobApplication.setResume(resume);
        jobApplication.setApplicant(applicant);
        jobApplication.setStatus(JobApplication.Status.Pending);
        jobApplication = jobApplicationRepo.save(jobApplication);

        emailService.sendEmail(account.getEmail(), applicant.getFullname(), JobApplication.Status.Pending,
                job.getCompany().getCompanyFullName(), job.getTitle(), LocalDateTime.now());

        return jobApplication;
    }

}
