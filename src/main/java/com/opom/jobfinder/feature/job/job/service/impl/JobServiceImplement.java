package com.opom.jobfinder.feature.job.job.service.impl;

import com.opom.jobfinder.feature.job.job.dto.JobDto;
import com.opom.jobfinder.feature.job.job.mapper.JobMapper;
import com.opom.jobfinder.feature.job.job.service.JobService;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.job.JobRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImplement implements JobService {
    private final JobRepo jobRepo;
    private final JobMapper jobMapper;

    @Override
    public List<JobDto> listAllJobs() {
        return jobRepo.findAll()
                .stream()
                .map(jobMapper::toJobDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobDto getJobById(UUID id) {
        Job job = jobRepo.findById(id).orElseThrow(() -> new BadRequestException("Job not found with id: " + id));
        return jobMapper.toJobDto(job);
    }

    @Override
    public JobDto createJob(JobDto jobDto) {
        Job job = jobMapper.toJob(jobDto);
        Job savedJob = jobRepo.save(job);
        return jobMapper.toJobDto(savedJob);
    }

    @Override
    public JobDto updateJob(JobDto jobDto, UUID id) {
        Job existingJob = jobRepo.findById(id).orElseThrow(() -> new BadRequestException("Job not found with id: " + id));
        existingJob.setTitle(jobDto.getTitle());
        existingJob.setDescription(jobDto.getDescription());
        existingJob.setPost(jobDto.getPost());
        existingJob.setRequirement(jobDto.getRequirement());
        existingJob.setSalary(jobDto.getSalary());
        existingJob.setAddress(jobDto.getAddress());
        existingJob.setType(jobDto.getType());
        existingJob.setJobStatus(jobDto.getJobStatus());
        Job updatedJob = jobRepo.save(existingJob);
        return jobMapper.toJobDto(updatedJob);
    }

    @Override
    public boolean deleteJob(UUID id) {
        if (jobRepo.existsById(id)) {
            jobRepo.deleteById(id);
            return true;
        }
        throw new BadRequestException("Job not found with id: " + id);
    }
}
