package com.opom.jobfinder.feature.admin.info.skill.service.impl;

import com.opom.jobfinder.feature.admin.info.skill.dto.SkillDto;
import com.opom.jobfinder.feature.admin.info.skill.mapper.SkillMapper;
import com.opom.jobfinder.feature.admin.info.skill.service.SkillService;
import com.opom.jobfinder.model.entity.info.Skill;
import com.opom.jobfinder.model.repo.info.SkillRepo;
import com.opom.jobfinder.utility.BaseService;
import com.opom.jobfinder.utility.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl extends BaseService implements SkillService {

    private final SkillRepo skillRepo;
    private final SkillMapper skillMapper;

    /**
     * List all skills
     */
    @Override
    public List<SkillDto> listAllSkills() {
        return skillRepo.findAll()
                .stream()
                .map(skillMapper::toSkillDto)
                .collect(Collectors.toList());
    }

    /**
     * Get skill by ID
     */
    @Override
    public SkillDto getSkillById(int id) {
        Skill skill = skillRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("Skill not found with ID: " + id));
        return skillMapper.toSkillDto(skill);
    }

    /**
     * Create a new skill
     */
    @Override
    public SkillDto createSkill(SkillDto skillDto) {
        Skill skill = skillMapper.toSkill(skillDto);
        Skill savedSkill = skillRepo.save(skill);
        return skillMapper.toSkillDto(savedSkill);
    }

    /**
     * Update an existing skill
     */
    @Override
    public SkillDto updateSkill(SkillDto skillDto, int id) {
        Skill existingSkill = skillRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("Skill not found with ID: " + id));

        // Update the existing skill entity with new data
        existingSkill.setName(skillDto.getName());
        existingSkill.setDescription(skillDto.getDescription());
        existingSkill.setUpdatedAt(skillDto.getUpdatedAt());
        existingSkill.setVersion(skillDto.getVersion());

        Skill updatedSkill = skillRepo.save(existingSkill);
        return skillMapper.toSkillDto(updatedSkill);
    }

    /**
     * Delete a skill by ID
     */
    @Override
    public boolean deleteSkill(int id) {
        if (skillRepo.existsById(id)) {
            skillRepo.deleteById(id);
            return true;
        }
        throw new BadRequestException("Skill not found with ID: " + id);
    }
}
