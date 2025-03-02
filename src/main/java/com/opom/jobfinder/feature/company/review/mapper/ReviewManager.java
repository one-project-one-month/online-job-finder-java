package com.opom.jobfinder.feature.company.review.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opom.jobfinder.feature.company.review.dtos.ReviewByCompanyDTO;
import com.opom.jobfinder.feature.company.review.dtos.ReviewByCompanyDTO.CompanyDTO;
import com.opom.jobfinder.feature.company.review.dtos.ReviewByCompanyDTO.ApplicantDTO;
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
    public ReviewByCompanyDTO toReviewByCompanyDTO(Review review) {
        try {
            CompanyDTO companyDTO = new CompanyDTO(
                    review.getCompany().getId(),
                    review.getCompany().getAccount().getName()
            );

            ApplicantDTO applicantDTO = new ApplicantDTO(
                    review.getApplicant().getId(),
                    review.getApplicant().getAccount().getName()
            );
            return new ReviewByCompanyDTO(
                    review.getComment(),
                    review.getRating(),
                    companyDTO,
                    applicantDTO
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
