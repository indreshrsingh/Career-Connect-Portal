package com.careerconnect.reviewms.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.getReviewsByCompany(companyId), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {

        if(reviewService.addReview(companyId, review))
        {
            return new ResponseEntity<>("Review Added",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }


     @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId)
     {
         return new ResponseEntity<>(reviewService.getReviewsById(reviewId), HttpStatus.OK);
     }

     @PutMapping("/{reviewId}")
     public  ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review)
     {
          if(reviewService.updateReview(reviewId,review))
              return new ResponseEntity<>("Review Updated",HttpStatus.OK);
          return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
     }


     @DeleteMapping("/{reviewId}")
     public ResponseEntity<String> deleteReview(@PathVariable Long reviewId)
     {
         if(reviewService.deleteReview(reviewId))
             return new ResponseEntity<>("Review Deleted",HttpStatus.OK);
         return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
     }

     @GetMapping("/averageRating")
     public ResponseEntity<Double> getAverageRating(@RequestParam Long companyId)
     {
          List<Review> reviews = reviewService.getReviewsByCompany(companyId);
          return new ResponseEntity<>(reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0),HttpStatus.OK);
     }




}
