package com.opom.jobfinder.feature.company.review.service.impl;

import com.opom.jobfinder.feature.company.review.service.ReviewService;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.review.ReviewRepo;
import com.opom.jobfinder.utility.BaseResponse;
import com.opom.jobfinder.utility.MessageConstants;
import com.opom.jobfinder.utility.Translator;
import com.opom.jobfinder.utility.exception.BadRequestException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    // CONSTANT VALUES
    private final ReviewRepo reviewRepo;
    private final CompanyRepo companyRepo;

    // CONSTRUCTOR
    public ReviewServiceImpl(ReviewRepo reviewRepo, CompanyRepo companyRepo) {
        this.reviewRepo = reviewRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public Review save(Review review, String companyId) {
        if(companyId == null || companyId.length() != 36) {
            throw new BadRequestException("Company Id is not valid!");
        } else {
            Optional<Company> company = companyRepo.findById(UUID.fromString(companyId));
            if (company.isPresent()) {
                review.setCompany(company.get());
            } else {
                throw new BadRequestException("Company Not Found!");
            }
        }
        return reviewRepo.save(review);
    }

    @Override
    public Review update(Review review,String companyId) {
        if(companyId == null || companyId.length() != 36) {
            throw new BadRequestException("Company Id is not valid!");
        } else {
            Optional<Company> company = companyRepo.findById(UUID.fromString(companyId));
            Optional<Review> review1 = reviewRepo.findById(review.getId());
            if (company.isPresent() && review1.isPresent()) {
                review.setCompany(company.get());
            } else {
                throw new BadRequestException("Company Not Found!");
            }
        }
        return reviewRepo.save(review);
    }

    @Override
    public void delete(String reviewId) {
        Optional<Review> review = reviewRepo.findById(UUID.fromString(reviewId));
        if (review.isPresent()) {
            review.get().setStatus(false);
            Review deletedReview = review.get();
            reviewRepo.save(deletedReview);
        } else {
            throw new BadRequestException("Review Not Found!");
        }
    }

    @Override
    public List<Review> getByCompany(String companyId) {
        Optional<Company> company = companyRepo.findById(UUID.fromString(companyId));
        if (company.isPresent()) {
            List<Review> reviews = reviewRepo.search(cb -> {
                CriteriaQuery<Review> query = cb.createQuery(Review.class);
                Root<Review> root = query.from(Review.class);
                query.select(root).where(cb.equal(root.get("company").get("id"), companyId));
                return query;
            });
            return reviews;
        } else {
            throw new BadRequestException("Company Not Found!");
        }
    }

    @Override
    public Double getAvgFromCompany(String companyId) {
        Optional<Company> company = companyRepo.findById(UUID.fromString(companyId));
        if (company.isPresent()) {
            Double averageRating = reviewRepo.search(cb -> {
                CriteriaQuery<Double> query = cb.createQuery(Double.class);
                Root<Review> root = query.from(Review.class);

                query.select(cb.avg(root.get("rating")))
                        .where(cb.equal(root.get("company").get("id"), companyId));

                return query;
            }).stream().findFirst().orElse(0.0);
            return averageRating;
        } else {
            throw new BadRequestException("Company Not Found!");
        }
    }
}
