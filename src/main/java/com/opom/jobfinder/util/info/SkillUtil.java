package com.opom.jobfinder.util.info;

import com.opom.jobfinder.dto.info.SkillDto;
import com.opom.jobfinder.model.entity.info.Skill;
import org.springframework.beans.BeanUtils;

public class SkillUtil {
    public static SkillDto skillDto(Skill skill) {
        SkillDto skillDto = new SkillDto();
        BeanUtils.copyProperties(skill, skillDto);
        return skillDto;
    }

    public static Skill toSkillEntity(SkillDto skillDto) {
        Skill skill = new Skill();
        BeanUtils.copyProperties(skillDto, skill);
        return skill;
    }
}
