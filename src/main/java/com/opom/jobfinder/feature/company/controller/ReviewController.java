package com.opom.jobfinder.feature.company.controller;

import com.opom.jobfinder.feature.company.service.ReviewService;
import com.opom.jobfinder.model.entity.company.Review;
import com.opom.jobfinder.utility.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiters")
public class ReviewController {

    // CONSTANT VALUES
    private final ReviewService reviewService;

    // CONSTRUCTOR
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> addReview(@RequestBody Review review, @PathVariable String id) {
        BaseResponse response = reviewService.save(review,id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> getReviewsByCompany(@PathVariable String id) {
        BaseResponse response = reviewService.getByCompany(id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> updateReview(@RequestBody Review review,@PathVariable String id) {
        BaseResponse response = reviewService.update(review, id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse> deleteReview(@RequestParam("id") String id) {
        BaseResponse response = reviewService.delete(id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}/reviews/avg")
    public ResponseEntity<BaseResponse> getAvgReviewOfCompany(@PathVariable String id) {
        BaseResponse response = reviewService.getAvgFromCompany(id);
        if(response.errorCode().equals("00000")) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
