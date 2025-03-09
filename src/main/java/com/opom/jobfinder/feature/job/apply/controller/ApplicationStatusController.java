package com.opom.jobfinder.feature.job.apply.controller;

import com.opom.jobfinder.feature.job.apply.service.ApplicationStatusService;
import com.opom.jobfinder.model.entity.job.JobApplication;
import com.opom.jobfinder.utility.BaseResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationStatusController {

    private final ApplicationStatusService applicationStatusService;

    @PostMapping("/{applicationId}/seen")
    public ResponseEntity<?> seenApplication(@PathVariable @NotBlank String applicationId) {
        return ResponseEntity.ok(BaseResponse.success(
                applicationStatusService.seenApplication(applicationId)
        ));
    }

    @PostMapping("/{applicationId}/status")
    public ResponseEntity<?> approveOrRejectApplication(@PathVariable @NotBlank String applicationId,
                                                        @RequestParam @NotNull JobApplication.Status status) {
        return ResponseEntity.ok(BaseResponse.success(
                applicationStatusService.approveOrRejectApplication(applicationId, status)
        ));
    }
}
