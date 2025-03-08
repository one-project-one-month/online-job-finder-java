package com.opom.jobfinder.feature.job.controller;

import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.feature.job.service.JobService;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobs")
@AllArgsConstructor
public class JobController {

    // CONSTANTS
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<BaseResponse> getJobsByLocations(
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "location", required = false, defaultValue = "") String location,
            @RequestParam(value = "q", required = false, defaultValue = "") String query) {
        List<GetJobByLocationDTO> response = jobService.getJobs(id);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }
}
