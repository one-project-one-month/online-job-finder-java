package com.opom.jobfinder.feature.job.save.controller;

import com.opom.jobfinder.feature.job.save.mapper.SaveJobManager;
import com.opom.jobfinder.feature.job.save.service.SaveJobService;
import com.opom.jobfinder.model.entity.applicant.SavedJob;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("saves")
public class SaveJobController {

    // CONSTANT
    private final SaveJobService saveJobService;
    private final SaveJobManager saveJobManager;

    @PostMapping
    public ResponseEntity<BaseResponse> saveJob(@RequestParam UUID jobID ) {
        String response = saveJobService.save(jobID);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, response, Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @PutMapping
    public ResponseEntity<BaseResponse> un_saveJob(@RequestParam UUID jobID ) {
        List<SavedJob> response = saveJobService.un_save(jobID);
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, saveJobManager.toSaveJobDTO(response), Translator.toLocale(MessageConstants.SUCCESS)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getSaveJobByUser() {
        List<SavedJob> response = saveJobService.getSaveJobsByApplicant();
        return ResponseEntity.ok(BaseResponse.of(MessageConstants.SUCCESS, saveJobManager.toSaveJobDTO(response), Translator.toLocale(MessageConstants.SUCCESS)));
    }

}
