package com.software.hereisdog.service;

import com.software.hereisdog.domain.Review;
import com.software.hereisdog.controller.ReviewForm;
import com.software.hereisdog.domain.Review;

import java.util.List;


/**
 * 리뷰 등록 및 조회 비즈니스 로직을 처리하는 서비스 인터페이스
 */

public interface ReviewService {
    void registerReview(Review review);                       // 리뷰 등록 또는 수정
    List<Review> getReviewsByPlaceId(Long placeId);           // 장소별 리뷰 조회
    List<Review> getReviewsByUserId(String userId);           // 유저별 리뷰 조회
 }
