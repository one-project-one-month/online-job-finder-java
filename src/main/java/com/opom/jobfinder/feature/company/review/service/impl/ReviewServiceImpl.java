package com.opom.jobfinder.feature.company.review.service.impl;

import com.opom.jobfinder.feature.applicant.service.ApplicantService;
import com.opom.jobfinder.feature.auth.service.AuthService;
import com.opom.jobfinder.feature.company.review.dtos.ReviewByCompanyDTO;
import com.opom.jobfinder.feature.company.review.mapper.ReviewManager;
import com.opom.jobfinder.feature.company.review.service.ReviewService;
import com.opom.jobfinder.model.entity.applicant.Applicant;
import com.opom.jobfinder.model.entity.company.Company;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.model.repo.company.CompanyRepo;
import com.opom.jobfinder.model.repo.review.ReviewRepo;
import com.opom.jobfinder.utility.exception.BadRequestException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    // CONSTANT VALUES
    private final ReviewRepo reviewRepo;
    private final CompanyRepo companyRepo;
    private final ReviewManager reviewManager;
    private final AuthService authService;
    private final ApplicantService applicantService;

    @Override
    public Review save(Review review, String companyId) {
        if(companyId == null || companyId.length() != 36) {
            throw new BadRequestException("Company Id is not valid!");
        } else {
            Optional<Company> company = companyRepo.findById(UUID.fromString(companyId));
            if (company.isPresent()) {
                Applicant applicant = applicantService.findById(authService.getLoginUserId());
                review.setApplicant(applicant);
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
    public List<ReviewByCompanyDTO> getByCompany(String companyId) {
        Optional<Company> company = companyRepo.findById(UUID.fromString(companyId));
        if (company.isPresent()) {
            List<Review> reviews = reviewRepo.findByCompanyId(UUID.fromString(companyId));
            List<ReviewByCompanyDTO> reviewByCompanyDTOS = new ArrayList<>();
            for (Review review : reviews) {
                reviewByCompanyDTOS.add(reviewManager.toReviewByCompanyDTO(review));
            }
            return reviewByCompanyDTOS;
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
                        .where(cb.equal(root.get("company").get("id"), UUID.fromString(companyId)));

                return query;
            }).stream().findFirst().orElse(0.0);
            return averageRating;
        } else {
            throw new BadRequestException("Company Not Found!");
        }
    }
}
