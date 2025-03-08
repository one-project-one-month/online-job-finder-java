package com.opom.jobfinder.feature.job.save.dtos;

import com.opom.jobfinder.model.entity.applicant.pk.SavedJobPk;

import java.util.UUID;

public record GetSaveJobByUserDTO(
    SavedJobPkDTO savedJobPk,
    JobDTO jobDTO
) {
    public record SavedJobPkDTO (
        UUID jobId,
        UUID applicantId
    ){}
    public record JobDTO(
         String title,
         String description
    ){}
}
