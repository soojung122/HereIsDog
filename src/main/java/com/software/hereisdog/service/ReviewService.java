package com.software.hereisdog.service;

import com.software.hereisdog.controller.ReviewForm;
import java.util.List;

/**
 * 리뷰 등록 및 조회 비즈니스 로직을 처리하는 서비스 인터페이스
 */

public interface ReviewService {
    void registerReview(Long placeId, ReviewForm reviewForm);
    List<Object> findByPlaceId(Long placeId);
}
