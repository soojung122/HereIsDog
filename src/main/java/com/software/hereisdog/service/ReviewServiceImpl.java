package com.software.hereisdog.service;

import com.software.hereisdog.dao.ReviewDAO;
import com.software.hereisdog.dao.mybatis.mapper.ReviewMapper;
import com.software.hereisdog.domain.Review;
import com.software.hereisdog.controller.ReviewForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * ReviewService 인터페이스 구현 클래스
 */

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void registerReview(Review review) {
        List<Review> existing = reviewMapper.findByPlaceIdAndUserId(review.getPlaceId(), review.getUserId());
        if (existing != null && !existing.isEmpty()) {
            reviewMapper.updateReview(review);  // 내용 갱신
        } else {
            reviewMapper.insertReview(review);  // 새로 등록
        }
    }

    @Override
    public List<Review> getReviewsByPlaceId(Long placeId) {
        return reviewMapper.findReviewsByPlaceId(placeId);
    }

    @Override
    public List<Review> getReviewsByUserId(String userId) {
        return reviewMapper.findReviewsByUserId(userId);
    }

}
