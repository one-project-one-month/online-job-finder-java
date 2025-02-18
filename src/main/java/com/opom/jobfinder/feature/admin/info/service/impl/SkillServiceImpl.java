package com.opom.jobfinder.feature.admin.info.service.impl;

import com.opom.jobfinder.dto.info.SkillDto;
import com.opom.jobfinder.feature.admin.info.service.SkillService;
import com.opom.jobfinder.model.entity.info.Skill;
import com.opom.jobfinder.model.repo.info.SkillRepo;
import com.opom.jobfinder.util.info.SkillUtil;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepo skillRepo;

    /**
     * List all skills
     */
    @Override
    public BaseResponse listAllSkills() {
        List<SkillDto> skills = skillRepo.findAll()
                .stream()
                .map(SkillUtil::skillDto)
                .collect(Collectors.toList());
        return BaseResponse.of(MessageConstants.SUCCESS, skills, Translator.toLocale(MessageConstants.SUCCESS));
    }

    /**
     * Get skill by ID
     */
    @Override
    public BaseResponse getSkillById(int id) {
        Optional<Skill> skillOptional = skillRepo.findById(id);
        if (skillOptional.isPresent()) {
            SkillDto skillDto = SkillUtil.skillDto(skillOptional.get());
            return BaseResponse.of(MessageConstants.SUCCESS, skillDto, Translator.toLocale(MessageConstants.SUCCESS));
        }
        return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
    }

    /**
     * Create a new skill
     */
    @Override
    public BaseResponse createSkill(SkillDto skillDto) {
        Skill skill = SkillUtil.toSkillEntity(skillDto);
        Skill savedSkill = skillRepo.save(skill);
        SkillDto savedSkillDto = SkillUtil.skillDto(savedSkill);

        return BaseResponse.of(MessageConstants.SUCCESS, savedSkillDto, Translator.toLocale(MessageConstants.SUCCESS));
    }

    /**
     * Update an existing skill
     */
    @Override
    public BaseResponse updateSkill(SkillDto skillDto, int id) {
        Optional<Skill> skillOptional = skillRepo.findById(id);
        if (skillOptional.isPresent()) {
            Skill skill = skillOptional.get();
            skill.setName(skillDto.getName()); // Update only necessary fields
            Skill updatedSkill = skillRepo.save(skill);
            SkillDto updatedSkillDto = SkillUtil.skillDto(updatedSkill);
            return BaseResponse.of(MessageConstants.SUCCESS, updatedSkillDto, Translator.toLocale(MessageConstants.SUCCESS));
        }
        return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
    }

    /**
     * Delete a skill by ID
     */
    @Override
    public BaseResponse deleteSkill(int id) {
        Optional<Skill> skillOptional = skillRepo.findById(id);
        if (skillOptional.isPresent()) {
            skillRepo.deleteById(id);
            return BaseResponse.of(MessageConstants.SUCCESS, null, Translator.toLocale(MessageConstants.SUCCESS));
        }
        return BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
    }
}
