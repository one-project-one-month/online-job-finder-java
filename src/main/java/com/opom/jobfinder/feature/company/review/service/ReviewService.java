package com.opom.jobfinder.feature.company.review.service;


import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;

import java.util.List;

public interface ReviewService {

    // ADD REVIEW
    Review save(Review review,String companyId);

    // UPDATE REVIEW
    Review update(Review review,String companyId);

    // DELETE REVIEW
    void delete(String reviewId);

    // GET REVIEWS BY COMPANY
    List<Review> getByCompany(String companyId);

    // GET AVERAGE REVIEW FROM COMPANY
    Double getAvgFromCompany(String companyId);
}