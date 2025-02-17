package com.opom.jobfinder.feature.applicant.service;


import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;

import java.util.UUID;

public interface ReviewService {
    
    BaseResponse addReview(Review review);
    BaseResponse findReviewById(String reviewId);
    BaseResponse updateReview(Review review);
    BaseResponse deleteReview(String reviewId);
    BaseResponse getReviewsByCompany(String companyId);
    BaseResponse getAverageReviewFromCompany(String companyId);
}