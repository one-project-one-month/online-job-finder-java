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
public class ApplicantJobCategoryPk {

    @Column(name = "job_category_id")
    private int jobCategoryId;

    @Column(name = "applicant_id")
    private UUID applicantId;
    
    public String getValue() {
    	return "%s-%s".formatted(jobCategoryId, applicantId);
    }
    
    public static ApplicantJobCategoryPk parse(String id) {
    	var array = id.split("-");
    	return new ApplicantJobCategoryPk(Integer.parseInt(array[0]), UUID.fromString(array[1]));
    }
}
