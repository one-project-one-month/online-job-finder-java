package com.opom.jobfinder.model.repo.review;

import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepo extends BaseRepository<Review, UUID> {
        List<Review> findByCompanyId(UUID id);
}
