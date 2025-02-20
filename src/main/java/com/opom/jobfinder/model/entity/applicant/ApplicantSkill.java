package com.opom.jobfinder.model.entity.applicant;

import com.opom.jobfinder.model.entity.AbstractEntity;
import com.opom.jobfinder.model.entity.applicant.pk.ApplicantSkillPk;
import com.opom.jobfinder.model.entity.info.Skill;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ApplicantSkill extends AbstractEntity {

	@EmbeddedId
	private ApplicantSkillPk id;
	
    @ManyToOne
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
	private Skill skill;
	
    @ManyToOne
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private Applicant applicant;
}
