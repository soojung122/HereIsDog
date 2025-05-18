package com.software.hereisdog.service;

import com.software.hereisdog.dao.ReviewDAO;
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

	@Autowired
    private ReviewDAO reviewDAO;

    @Override
    public void registerReview(Long placeId, ReviewForm reviewForm) {
        // ReviewForm → Review 변환 후 DAO로 전달
        // (Customer는 로그인 세션에서 꺼내와야 할 수도 있음. 예시로 null)
        Review review = new Review();
        review.setContent(reviewForm.getContent());
        review.setRating(reviewForm.getRating());
        // place, customer 세팅 필요 (실제 서비스 로직에서 처리)
        // review.setPlace(...); review.setCustomer(...);
        // reviewDAO.insertReview(review); // 실제 등록
    }

    @Override
    public List<ReviewForm> findByPlaceId(Long placeId) {
        List<Review> reviews = reviewDAO.findByPlaceId(placeId);
        List<ReviewForm> result = new ArrayList<>();
        for (Review review : reviews) {
            ReviewForm form = new ReviewForm();
            form.setContent(review.getContent());
            form.setRating(review.getRating());
            // 추가 필드 필요하면 여기에 세팅
            result.add(form);
        }
        return result;
    }
}
