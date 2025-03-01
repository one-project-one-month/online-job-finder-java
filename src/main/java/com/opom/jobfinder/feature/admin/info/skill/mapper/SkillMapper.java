package com.opom.jobfinder.feature.admin.info.skill.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.admin.info.skill.dto.SkillDto;
import com.opom.jobfinder.model.entity.info.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillMapper {

    private final ObjectMapper objectMapper; // ObjectMapper is injected via constructor

    public Skill toSkill(Object data) {
        try {
            // Convert the input data (could be a Map, JSON, etc.) into a Skill object
            return objectMapper.convertValue(data, Skill.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to map data to Skill", e); // Specific exception handling
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during mapping", e);
        }
    }

    public SkillDto toSkillDto(Skill skill) {
        try {
            return objectMapper.convertValue(skill, SkillDto.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to map Skill to SkillDto", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during mapping", e);
        }
    }
}
