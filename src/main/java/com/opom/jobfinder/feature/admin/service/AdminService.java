package com.opom.jobfinder.feature.admin.service;

import com.opom.jobfinder.dto.info.SkillDto;

import java.util.List;

public interface AdminService{

    List<SkillDto> listAllSkills();
    SkillDto getSkillById(int id);
    SkillDto createSkill(SkillDto skillDto);
    SkillDto updateSkill(SkillDto skillDto, int id);
    void deleteSkill(int id);

}
