package com.opom.jobfinder.feature.job.mapper;

import com.opom.jobfinder.feature.admin.location.dtos.GetJobByLocationDTO;
import com.opom.jobfinder.feature.job.dtos.JobDTO;
import com.opom.jobfinder.model.entity.job.Job;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobManager {

    public static JobDTO toJobDTO(Job job) {
        try {
            return new JobDTO(
                    job.getTitle(),
                    job.getDescription(),
                    job.getPost(),
                    job.getAddress(),
                    job.getType(),
                    job.getJobStatus().name()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
