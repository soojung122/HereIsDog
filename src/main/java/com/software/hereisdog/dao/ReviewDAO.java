package com.software.hereisdog.dao;

import com.software.hereisdog.domain.Review;
import java.util.List;

public interface ReviewDAO {
	List<Review> findByPlaceId(Long placeId);
	List<Review> findByCustomerId(Long customerId);
}