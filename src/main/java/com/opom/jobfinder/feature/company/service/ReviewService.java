package com.opom.jobfinder.feature.company.service;


import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;

public interface ReviewService {

    // ADD REVIEW
    BaseResponse addReview(Review review);

    // FIND REVIEW BY ID
    BaseResponse findReviewById(String reviewId);

    // UPDATE REVIEW
    BaseResponse updateReview(Review review);

    // DELETE REVIEW
    BaseResponse deleteReview(String reviewId);

    // GET REVIEWS BY COMPANY
    BaseResponse getReviewsByCompany(String companyId);

    // GET AVERAGE REVIEW FROM COMPANY
    BaseResponse getAverageReviewFromCompany(String companyId);
}