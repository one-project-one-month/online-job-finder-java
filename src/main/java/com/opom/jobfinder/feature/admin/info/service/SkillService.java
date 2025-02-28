package com.opom.jobfinder.feature.admin.info.service;

import com.opom.jobfinder.dto.info.SkillDto;
import com.opom.jobfinder.utility.BaseResponse;

public interface SkillService {
    BaseResponse listAllSkills();
    BaseResponse getSkillById(int id);
    BaseResponse createSkill(SkillDto skillDto);
    BaseResponse updateSkill(SkillDto skillDto, int id);
    BaseResponse deleteSkill(int id);
}
