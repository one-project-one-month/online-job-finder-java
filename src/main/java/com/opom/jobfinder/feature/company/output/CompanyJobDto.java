package com.opom.jobfinder.feature.company.output;

import com.opom.jobfinder.model.entity.job.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyJobDto {

    private UUID id;
    private String title;
    private String description;
    private int post;
    private Double salary;
    private String address;

    private Job.Type type;
    private Job.Status jobStatus;

    public static CompanyJobDto from(Job job) {

        return CompanyJobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .post(job.getPost())
                .salary(job.getSalary())
                .address(job.getAddress())
                .type(job.getType())
                .jobStatus(job.getJobStatus())
                .build();
    }
}
