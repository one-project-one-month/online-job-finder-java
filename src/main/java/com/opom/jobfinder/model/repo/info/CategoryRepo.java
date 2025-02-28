package com.opom.jobfinder.model.repo.info;

import com.opom.jobfinder.model.entity.info.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<JobCategory,Integer> {
}
