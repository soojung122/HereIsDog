package com.software.hereisdog.dao;

import com.software.hereisdog.domain.Review;
import java.util.List;

public interface ReviewDAO {
	//void save(Review review); 
	List<Review> findByPlaceId(Long placeId);
}