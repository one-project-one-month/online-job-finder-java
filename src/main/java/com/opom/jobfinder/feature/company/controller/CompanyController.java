package com.opom.jobfinder.feature.company.controller;

import com.opom.jobfinder.feature.company.service.ReviewService;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiters")
public class CompanyController {

    // CONSTANT VALUES
    private final ReviewService reviewService;

    // CONSTRUCTOR
    public CompanyController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> addReview(@RequestBody Review review,@PathVariable String id) {
        return ResponseEntity.ok(reviewService.addReview(review,id));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> getReviews(@PathVariable String id) {
        return ResponseEntity.ok(reviewService.getReviewsByCompany(id));
    }

    @PutMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> updateReview(@RequestBody Review review,@PathVariable String id) {
      return ResponseEntity.ok(reviewService.updateReview(review, id));
    }

    @DeleteMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> deleteReview(@RequestParam("id") String id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }

    @GetMapping("/{id}/reviews/avg")
    public ResponseEntity<BaseResponse> getAvgReviewOfCompany(@PathVariable String id) {
        return ResponseEntity.ok(reviewService.getAverageReviewFromCompany(id));
    }
}
