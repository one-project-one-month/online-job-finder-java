package com.opom.jobfinder.feature.company.review.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.model.entity.company.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewManager {

    private final ObjectMapper mapper;

    public Review toReview(Object obj) {
        try {
            return mapper.convertValue(obj,Review.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
