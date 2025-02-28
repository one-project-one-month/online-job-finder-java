package com.opom.jobfinder.model.repo.info;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opom.jobfinder.model.entity.info.Skill;


public interface SkillRepo extends JpaRepository<Skill,Integer> {
}
