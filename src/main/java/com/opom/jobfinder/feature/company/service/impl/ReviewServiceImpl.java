package com.opom.jobfinder.feature.company.service.impl;

import com.opom.jobfinder.feature.company.service.ReviewService;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.review.ReviewRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    // CONSTANT VALUES
    private final ReviewRepo reviewRepo;

    // CONSTRUCTOR
    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public BaseResponse addReview(Review review) {
        return BaseResponse.of(MessageConstants.SUCCESS,reviewRepo.save(review), Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse findReviewById(String reviewId) {
        return BaseResponse.of(MessageConstants.SUCCESS,reviewRepo.findById(UUID.fromString(reviewId)), Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse updateReview(Review review) {
        return BaseResponse.of(MessageConstants.SUCCESS,reviewRepo.save(review), Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse deleteReview(String reviewId) {
        reviewRepo.deleteById(UUID.fromString(reviewId));
        return BaseResponse.of(MessageConstants.SUCCESS, "Delete review successfully!", Translator.toLocale(MessageConstants.SUCCESS));
    }

    @Override
    public BaseResponse getReviewsByCompany(String companyId) {
        try {
            List<Review> reviews = reviewRepo.search(cb -> {
                CriteriaQuery<Review> query = cb.createQuery(Review.class);
                Root<Review> root = query.from(Review.class);
                query.select(root).where(cb.equal(root.get("company").get("id"), companyId));
                return query;
            });
            return BaseResponse.of(MessageConstants.SUCCESS,reviews,Translator.toLocale(MessageConstants.SUCCESS));
        } catch (Exception e) {
            throw new BadRequestException("Searching Jobs by Location Failed!"+e.getMessage());
        }
    }

    @Override
    public BaseResponse getAverageReviewFromCompany(String companyId) {
        try {
            Double averageRating = reviewRepo.search(cb -> {
                CriteriaQuery<Double> query = cb.createQuery(Double.class);
                Root<Review> root = query.from(Review.class);

                query.select(cb.avg(root.get("rating")))
                        .where(cb.equal(root.get("company").get("id"), companyId));

                return query;
            }).stream().findFirst().orElse(0.0);

            return BaseResponse.of(MessageConstants.SUCCESS, averageRating ,Translator.toLocale(MessageConstants.SUCCESS));
        } catch (Exception e) {
            throw new BadRequestException("Calculating Average Review Failed! " + e.getMessage());
        }
    }
}
