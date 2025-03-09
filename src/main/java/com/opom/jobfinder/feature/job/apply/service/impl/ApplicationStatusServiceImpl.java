package com.opom.jobfinder.feature.job.apply.service.impl;

import com.opom.jobfinder.feature.job.apply.service.ApplicationStatusService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.entity.job.JobApplication;
import com.opom.jobfinder.model.entity.job.pk.JobApplicationPk;
import com.opom.jobfinder.model.repo.job.JobApplicationRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import com.opom.jobfinder.utility.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final JobApplicationRepo jobApplicationRepo;
    private final EmailService emailService;

    @Override
    public JobApplication approveOrRejectApplication(String id, JobApplication.Status status) {
        if (status.equals(JobApplication.Status.Pending) ||
        status.equals(JobApplication.Status.Seen)) {
            throw new BadRequestException("Something went wrong.");
        }
        JobApplication jobApplication = getJobApplication(id);
        return updateStatusAndSendMail(jobApplication, status);

    }

    @Override
    public JobApplication seenApplication(String id) {
        JobApplication jobApplication = getJobApplication(id);
        if (!jobApplication.getStatus().equals(JobApplication.Status.Pending)) {
            throw new BadRequestException("Something went wrong.");
        }
        return updateStatusAndSendMail(jobApplication, JobApplication.Status.Seen);
    }

    private JobApplication getJobApplication(String id) {
        JobApplicationPk jobApplicationPk = JobApplicationPk.setValue(id);

        return jobApplicationRepo.findById(jobApplicationPk)
                .orElseThrow(() -> new BadRequestException("Application not found."));
    }

    private JobApplication updateStatusAndSendMail(JobApplication jobApplication, JobApplication.Status status) {
        jobApplication.setStatus(status);
        jobApplication = jobApplicationRepo.save(jobApplication);

        Applicant applicant = jobApplication.getApplicant();
        String email = applicant.getAccount().getEmail();
        Job job = jobApplication.getJob();
        Company company = job.getCompany();

        emailService.sendEmail(email, applicant.getFullname(), status,
                company.getCompanyFullName(), job.getTitle(), jobApplication.getCreatedAt());

        return jobApplication;
    }
}

