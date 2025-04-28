package com.software.hereisdog.service;

import com.software.hereisdog.controller.ReviewForm;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;

/**
 * ReviewService 인터페이스 구현 클래스
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public void registerReview(Long placeId, ReviewForm reviewForm) {
        // TODO: MyBatis DAO를 이용하여 리뷰 등록
    }

    @Override
    public List<Object> findByPlaceId(Long placeId) {
        // TODO: MyBatis DAO를 이용하여 특정 장소 리뷰 조회
        return Collections.emptyList();
    }
}
