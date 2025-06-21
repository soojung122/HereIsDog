package com.software.hereisdog.service;

import com.software.hereisdog.dao.ReviewDAO;
import com.software.hereisdog.dao.mybatis.mapper.ReviewMapper;
import com.software.hereisdog.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        // placeName과 address, userId가 모두 일치하는 리뷰가 있는지 확인
        List<Review> existing = reviewMapper.findByPlaceAndUser(
            review.getPlaceName(),
            review.getPlaceAddress(),
            review.getUserId()
        );
        if (existing != null && !existing.isEmpty()) {
            reviewMapper.updateReview(review); // 이미 있으면 수정
        } else {
            reviewMapper.insertReview(review); // 없으면 새로 등록
        }
    }

    @Override
    public List<Review> getReviewsByPlace(String placeName, String address) {
        return reviewMapper.findReviewsByPlace(placeName, address);
    }

    @Override
    public List<Review> getReviewsByUserId(String userId) {
        return reviewMapper.findReviewsByUserId(userId);
    }

    @Override
    public List<Review> findByCustomerId(Long customerId) {
        return reviewDAO.findByCustomerId(customerId);
    }
}