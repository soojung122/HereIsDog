package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Insert("INSERT INTO review (id, place_id, user_id, rating, content) " +
            "VALUES (review_seq.NEXTVAL, #{placeId}, #{userId}, #{rating}, #{content})")
    void insertReview(Review review);

    @Select("SELECT * FROM review WHERE place_id = #{placeId}")
    List<Review> findReviewsByPlaceId(@Param("placeId") Long placeId);

    @Select("SELECT * FROM review WHERE place_id = #{placeId} AND user_id = #{userId}")
    List<Review> findByPlaceIdAndUserId(@Param("placeId") Long placeId, @Param("userId") String userId);
    
    @Update("UPDATE review SET rating = #{rating}, content = #{content} WHERE place_id = #{placeId} AND user_id = #{userId}")
    void updateReview(Review review);
    
    @Select("SELECT * FROM review WHERE user_id = #{userId}")
    List<Review> findReviewsByUserId(@Param("userId") String userId);
}
