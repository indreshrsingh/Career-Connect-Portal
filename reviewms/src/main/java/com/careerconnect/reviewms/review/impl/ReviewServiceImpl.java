package com.careerconnect.reviewms.review.impl;



import com.careerconnect.reviewms.review.Review;
import com.careerconnect.reviewms.review.ReviewRepository;
import com.careerconnect.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    @Override
    public List<Review> getReviewsByCompany(Long companyId) {

        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Boolean addReview(Long companyId, Review review) {

        if(companyId!=null && review!=null)
        {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;

    }

    @Override
    public Review getReviewsById(Long reviewId) {

        if(reviewId!=null )
        {
            return reviewRepository.findById(reviewId).orElse(null);
        }
        return null;
    }

    @Override
    public Boolean updateReview(Long reviewId, Review updatedReview) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null)
        {
            review.setCompanyId(updatedReview.getCompanyId());
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        reviewRepository.findById(reviewId).ifPresent(review -> reviewRepository.deleteById(reviewId));
        return false;
    }


}
