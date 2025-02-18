package com.opom.jobfinder.feature.admin.info.controller;

import com.opom.jobfinder.dto.info.SkillDto;
import com.opom.jobfinder.feature.admin.info.service.SkillService;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        BaseResponse response = skillService.listAllSkills();
        return ResponseEntity.ok(response);
    }

    /**
     * Get skill by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getSkillById(@PathVariable("id") int id) {
        BaseResponse response = skillService.getSkillById(id);
        if (response.errorCode().equals(MessageConstants.BAD_REQUEST_ERROR)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Create a new skill
     */
    @PostMapping
    public ResponseEntity<BaseResponse> createSkill(@Valid @RequestBody SkillDto skillDto) {
        BaseResponse response = skillService.createSkill(skillDto);
        if (response.errorCode().equals(MessageConstants.SUCCESS)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Update an existing skill
     */
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateSkill(@Valid @RequestBody SkillDto skillDto, @PathVariable int id) {
        BaseResponse response = skillService.updateSkill(skillDto, id);
        if (response.errorCode().equals(MessageConstants.BAD_REQUEST_ERROR)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a skill
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteSkill(@PathVariable("id") int id) {
        BaseResponse response = skillService.deleteSkill(id);
        if (response.errorCode().equals(MessageConstants.BAD_REQUEST_ERROR)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
