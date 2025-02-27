package com.opom.jobfinder.config;

import com.opom.jobfinder.model.entity.account.Role;
import com.opom.jobfinder.model.repo.account.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepo roleRepo;
    @Override
    public void run(String... args) throws Exception {
        if (roleRepo.findByName("ADMIN").isEmpty()) {
            roleRepo.save(new Role("ADMIN"));
        }
        if (roleRepo.findByName("APPLICANT").isEmpty()) {
            roleRepo.save(new Role("APPLICANT"));
        }
        if (roleRepo.findByName("RECRUITER").isEmpty()) {
            roleRepo.save(new Role("RECRUITER"));
        }
    }
}
