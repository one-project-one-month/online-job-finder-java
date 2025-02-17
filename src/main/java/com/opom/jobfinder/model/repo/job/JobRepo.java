package com.opom.jobfinder.model.repo.job;

import com.opom.jobfinder.model.entity.job.Job;
import com.opom.jobfinder.model.repo.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepo extends BaseRepository<Job, UUID> {
}
