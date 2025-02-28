package com.opom.jobfinder.feature.admin.service.impl;

import com.opom.jobfinder.dto.info.SkillDto;
import com.opom.jobfinder.feature.admin.service.AdminService;
import com.opom.jobfinder.model.entity.info.Skill;
import com.opom.jobfinder.model.repo.info.SkillRepo;
import com.opom.jobfinder.util.info.SkillUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final SkillRepo skillRepo;

    public List<SkillDto> listAllSkills() {
        return skillRepo
                .findAll()
                .stream()
                .map(SkillUtil::skillDto)
                .collect(Collectors.toList());
    }

    public SkillDto getSkillById(int id) {
        Skill skill = skillRepo.findById(id).orElseThrow();
        return SkillUtil.skillDto(skill);
    }

    public SkillDto createSkill(SkillDto skillDto) {
        Skill skill = skillRepo.save(SkillUtil.toSkillEntity((skillDto)));
        return SkillUtil.skillDto(skill);
    }

    public SkillDto updateSkill(SkillDto skillDto, int id) {
        Skill skill = skillRepo.findById(id).orElseThrow();
        skill.setName(skillDto.getName());
        skillRepo.save(skill);
        return SkillUtil.skillDto(skill);
    }

    public void deleteSkill(int id) {
        skillRepo.deleteById(id);
    }
}
