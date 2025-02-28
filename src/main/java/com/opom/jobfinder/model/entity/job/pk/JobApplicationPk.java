package com.opom.jobfinder.model.entity.job.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
public class JobApplicationPk {

	@Column(name = "job_id")
	private UUID jobId;
	
	@Column(name = "applicant_id")
	private UUID applicantId;

	public String getValue() {
		return "%s-%s".formatted(jobId, applicantId);
	}
	
//	public static JobApplicationPk parse(String id) {
//		var array = id.split("-");
//		return new JobApplicationPk(UUID.fromString(array[0]), UUID.fromString(array[1]));
//	}
}
