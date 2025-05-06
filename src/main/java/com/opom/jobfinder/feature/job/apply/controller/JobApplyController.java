package com.opom.jobfinder.feature.job.apply.controller;

import com.opom.jobfinder.feature.job.apply.service.JobApplyService;
import com.opom.jobfinder.utility.BaseResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobApplyController {

    private final JobApplyService jobApplyService;

    @PostMapping("/{jobId}/apply")
    public ResponseEntity<?> applyJob(@PathVariable @NotNull UUID jobId, @RequestParam @NotNull @Min(1) Integer resumeId) {
        return ResponseEntity.ok(BaseResponse.success(jobApplyService.applyJob(jobId, resumeId)));
    }
}
