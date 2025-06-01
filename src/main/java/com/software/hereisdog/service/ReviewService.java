package com.software.hereisdog.service;

import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.domain.Review;

import java.util.List;

/**
 * 리뷰 등록 및 조회 비즈니스 로직을 처리하는 서비스 인터페이스
 */

public interface ReviewService {
    void registerReview(Review review);  // 이거 꼭 있어야 함
    List<Review> getReviewsByPlaceId(Long placeId);
    List<Review> getReviewsByUserId(String userId);
}
