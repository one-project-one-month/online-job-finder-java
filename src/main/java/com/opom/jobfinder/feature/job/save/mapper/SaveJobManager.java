package com.opom.jobfinder.feature.job.save.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.job.save.dtos.GetSaveJobByUserDTO;
import com.opom.jobfinder.model.entity.applicant.SavedJob;
import com.opom.jobfinder.model.entity.info.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SaveJobManager {

    private final ObjectMapper objectMapper;

    public List<GetSaveJobByUserDTO> toSaveJobDTO(List<SavedJob> savedJobs) {
       return savedJobs.stream()
               .map(savedJob -> new GetSaveJobByUserDTO(
                       new GetSaveJobByUserDTO.SavedJobPkDTO(
                               savedJob.getId().getJobId(),
                               savedJob.getId().getApplicantId()
                       ),
                       new GetSaveJobByUserDTO.JobDTO(
                               savedJob.getJob().getTitle(),
                               savedJob.getJob().getDescription()
                       )
               ))
               .collect(Collectors.toList());
    }
}
