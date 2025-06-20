package com.software.hereisdog.service;

import com.software.hereisdog.controller.ReviewForm;
import java.util.List;

/**
 * 리뷰 등록 및 조회 비즈니스 로직을 처리하는 서비스 인터페이스
 */

public interface ReviewService {
<<<<<<< Updated upstream
    void registerReview(Long placeId, ReviewForm reviewForm);
    List<ReviewForm> findByPlaceId(Long placeId);   // List<Object> → List<ReviewForm>
=======
    void registerReview(Review review);                       // 리뷰 등록 또는 수정
    List<Review> getReviewsByPlaceId(String placeId);           // 장소별 리뷰 조회
    List<Review> getReviewsByUserId(String userId);           // 유저별 리뷰 조회
    //List<Review> findByCustomerId(Long customerId);
    Double getAverageRatingByPlaceId(String placeId);
>>>>>>> Stashed changes
}
