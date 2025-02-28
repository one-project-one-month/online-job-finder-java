package com.opom.jobfinder.feature.admin.controller;

import com.opom.jobfinder.dto.info.SkillDto;
import com.opom.jobfinder.feature.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * Get all skills
     */
    @GetMapping("/skills")
    public ResponseEntity<List<SkillDto>> listAllSkills() {
        List<SkillDto> skills = adminService.listAllSkills();
        return ResponseEntity.ok(skills);
    }

    /**
     * Get skill by ID
     */
    @GetMapping("/skills/{id}")
    public ResponseEntity<?> getSkillById(@PathVariable("id") int id) {
        try {
            SkillDto skill = adminService.getSkillById(id);
            return ResponseEntity.ok(skill);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill with ID " + id + " not found.");
        }
    }

    /**
     * Create a new skill
     */
    @PostMapping("/skills")
    public ResponseEntity<?> createSkill(@Valid @RequestBody SkillDto skillDto) {
        try {
            SkillDto createdSkill = adminService.createSkill(skillDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSkill);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create skill.");
        }
    }

    /**
     * Update an existing skill
     */
    @PutMapping("/skills/{id}")
    public ResponseEntity<?> updateSkill(@Valid @RequestBody SkillDto skillDto, @PathVariable int id) {
        try {
            SkillDto updatedSkill = adminService.updateSkill(skillDto, id);
            return ResponseEntity.ok(updatedSkill);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update skill.");
        }
    }

    /**
     * Delete a skill
     */
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable("id") int id) {
        try {
            adminService.deleteSkill(id);
            return ResponseEntity.ok("Skill with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Skill with ID " + id + " not found.");
        }
    }
}
