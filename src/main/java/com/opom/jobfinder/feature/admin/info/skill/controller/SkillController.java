package com.opom.jobfinder.feature.admin.info.skill.controller;

import com.opom.jobfinder.feature.admin.info.skill.dto.SkillDto;
import com.opom.jobfinder.feature.admin.info.skill.service.SkillService;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/skills")
public class SkillController {

    private final SkillService skillService;

    /**
     * Get all skills
     */
    @GetMapping
    public ResponseEntity<BaseResponse> listAllSkills() {
        List<SkillDto> skills = skillService.listAllSkills();
        BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, skills, Translator.toLocale(MessageConstants.SUCCESS));
        return ResponseEntity.ok(response);
    }

    /**
     * Get skill by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getSkillById(@PathVariable("id") int id) {
        SkillDto skillDto = skillService.getSkillById(id);
        if (skillDto != null) {
            BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, skillDto, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.ok(response);
        }
        BaseResponse response = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Create a new skill
     */
    @PostMapping
    public ResponseEntity<BaseResponse> createSkill(@Valid @RequestBody SkillDto skillDto) {
        SkillDto createdSkill = skillService.createSkill(skillDto);
        if (createdSkill != null) {
            BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, createdSkill, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        BaseResponse response = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Update an existing skill
     */
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateSkill(@Valid @RequestBody SkillDto skillDto, @PathVariable int id) {
        SkillDto updatedSkill = skillService.updateSkill(skillDto, id);
        if (updatedSkill != null) {
            BaseResponse response = BaseResponse.of(MessageConstants.SUCCESS, updatedSkill, Translator.toLocale(MessageConstants.SUCCESS));
            return ResponseEntity.ok(response);
        }
        BaseResponse response = BaseResponse.of(MessageConstants.BAD_REQUEST_ERROR, null, Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Delete a skill
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteSkill(@PathVariable("id") int id) {
        boolean isDeleted = skillService.deleteSkill(id);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.of(
                            MessageConstants.BAD_REQUEST_ERROR,
                            null,
                            Translator.toLocale(MessageConstants.BAD_REQUEST_ERROR)
                    ));
        }

        return ResponseEntity.ok(BaseResponse.of(
                MessageConstants.SUCCESS,
                null,
                Translator.toLocale(MessageConstants.SUCCESS)
        ));
    }

}
