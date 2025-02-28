package com.opom.jobfinder.model.entity.applicant.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor

public class SavedJobPk {

    @Column(name = "job_id")
    private UUID jobId;

    @Column(name = "applicant_id")
    private UUID applicantId;

    public String getValue() {
        return "%s-%s".formatted(jobId, applicantId);
    }

    public static SavedJobPk parse(String id) {
        var array = id.split("-");
        return new SavedJobPk(UUID.fromString(array[0]), UUID.fromString(array[1]));
    }
}
