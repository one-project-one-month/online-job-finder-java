package com.opom.jobfinder.feature.company.review.service;


import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;

public interface ReviewService {

    // ADD REVIEW
    BaseResponse save(Review review,String companyId);

    // UPDATE REVIEW
    BaseResponse update(Review review,String companyId);

    // DELETE REVIEW
    BaseResponse delete(String reviewId);

    // GET REVIEWS BY COMPANY
    BaseResponse getByCompany(String companyId);

    // GET AVERAGE REVIEW FROM COMPANY
    BaseResponse getAvgFromCompany(String companyId);
}