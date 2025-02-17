package com.opom.jobfinder.model.entity.applicant.pk;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@AllArgsConstructor
public class ApplicantSkillPk {

	@Column(name = "skill_id")
    private int skillId;

    @Column(name = "applicant_id")
    private UUID applicantId;

    public String getValue() {
    	return "%s-%s".formatted(skillId, applicantId);
    }
    
    public static ApplicantSkillPk parse(String id) {
    	var array = id.split("-");
    	return new ApplicantSkillPk(Integer.parseInt(array[0]), UUID.fromString(array[1]));
    }
}
