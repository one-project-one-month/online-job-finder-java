package com.opom.jobfinder.feature.admin.info.skill.service;

import com.opom.jobfinder.feature.admin.info.skill.dto.SkillDto;
import java.util.List;

public interface SkillService {
    List<SkillDto> listAllSkills();
    SkillDto getSkillById(int id);
    SkillDto createSkill(SkillDto skillDto);
    SkillDto updateSkill(SkillDto skillDto, int id);
    boolean deleteSkill(int id);
}
