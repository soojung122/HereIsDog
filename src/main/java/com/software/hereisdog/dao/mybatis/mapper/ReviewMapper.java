package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Insert("INSERT INTO review (id, user_id, rating, content, placename, address) " +
            "VALUES (review_seq.NEXTVAL, #{userId}, #{rating}, #{content}, #{placeName}, #{placeAddress})")
    void insertReview(Review review);

    @Select("SELECT * FROM review WHERE placename = #{placeName} AND address = #{placeAddress}")
    @Result(property = "userId", column = "user_id")
    List<Review> findReviewsByPlace(@Param("placeName") String placeName, @Param("placeAddress") String placeAddress);

    @Select("SELECT * FROM review WHERE placename = #{placeName} AND address = #{placeAddress} AND user_id = #{userId}")
    List<Review> findByPlaceAndUser(@Param("placeName") String placeName, @Param("placeAddress") String placeAddress, @Param("userId") String userId);

    @Update("UPDATE review SET rating = #{rating}, content = #{content} WHERE placename = #{placeName} AND address = #{placeAddress} AND user_id = #{userId}")
    void updateReview(Review review);


    @Select("SELECT * FROM review WHERE user_id = #{userId}")
    List<Review> findReviewsByUserId(@Param("userId") String userId);
}

