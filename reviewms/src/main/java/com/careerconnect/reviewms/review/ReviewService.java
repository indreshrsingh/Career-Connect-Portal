package com.careerconnect.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByCompany(Long companyId);

    Boolean addReview(Long companyId, Review review);

    Review getReviewsById(Long reviewId);

    Boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);
}
