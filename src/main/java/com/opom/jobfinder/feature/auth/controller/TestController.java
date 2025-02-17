package com.opom.jobfinder.feature.auth.controller;

import com.opom.jobfinder.model.repo.account.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abc")
@RequiredArgsConstructor
public class TestController {
    private final RoleRepo repo;

    @GetMapping("/testing")
    public ResponseEntity<?> testing() {
        return ResponseEntity.ok("SUCCESS");
    }
}
