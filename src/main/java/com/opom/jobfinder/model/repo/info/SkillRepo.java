package com.opom.jobfinder.model.repo.skill;

import com.opom.jobfinder.model.entity.info.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill,String> {
}
