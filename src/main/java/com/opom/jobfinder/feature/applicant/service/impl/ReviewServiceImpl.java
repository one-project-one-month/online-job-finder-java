package com.opom.jobfinder.feature.applicant.service.impl;

import com.opom.jobfinder.feature.applicant.service.ReviewService;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Override
    public BaseResponse addReview(Review review) {
        return null;
    }

    @Override
    public BaseResponse findReviewById(String reviewId) {
        return null;
    }

    @Override
    public BaseResponse updateReview(Review review) {
        return null;
    }

    @Override
    public BaseResponse deleteReview(String reviewId) {
        return null;
    }

    @Override
    public BaseResponse getReviewsByCompany(String companyId) {
        return null;
    }

    @Override
    public BaseResponse getAverageReviewFromCompany(String companyId) {
        return null;
    }
}
