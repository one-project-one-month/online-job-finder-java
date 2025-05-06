package com.opom.jobfinder.feature.job.job.controller;

import com.opom.jobfinder.feature.job.job.dto.JobDto;
import com.opom.jobfinder.feature.job.job.service.JobService;
import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<BaseResponse> listAllJobs() {
        List<JobDto> jobs = jobService.listAllJobs();
        BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, jobs, Translator.toLocale(MessageConstants.SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getJobById(@PathVariable("id") UUID id) {
        JobDto job = jobService.getJobById(id);
        if (job != null) {
            BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, job, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        BaseResponse response = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createJob(@Valid @RequestBody JobDto jobDto) {
        JobDto createdJob = jobService.createJob(jobDto);
        if (createdJob != null) {
            BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, createdJob, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        BaseResponse response = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateJob(@Valid @RequestBody JobDto jobDto, @PathVariable("id") UUID id) {
        JobDto updatedJob = jobService.updateJob(jobDto, id);
        if (updatedJob != null) {
            BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, updatedJob, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        BaseResponse response = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteJobById(@PathVariable("id") UUID id) {
        boolean isDeleted = jobService.deleteJob(id);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.of(
                            MessageConstants.BAD_REQUEST_ERROR,
                            null,
                            Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR)
                    ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.of(MessageConstants.SUCCESS, id, Translator.toLocale(MessageConstants.SUCCESS)));
    }
}
