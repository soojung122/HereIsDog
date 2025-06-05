package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    // 1. 특정 유저의 리뷰 리스트 조회
    @Select("SELECT * FROM reviews WHERE customer_id = #{customerId}")
    List<Review> findByCustomerId(Long customerId);

    // 2. 리뷰 단건 조회
    @Select("SELECT * FROM reviews WHERE id = #{id}")
    Review findById(Long id);

    // 3. 리뷰 작성(삽입)
    @Insert("INSERT INTO reviews (id, content, rating, place_id, customer_id) " +
            "VALUES (#{id}, #{content}, #{rating}, #{place.id}, #{customer.id})")
    @SelectKey(statement = "SELECT review_seq.NEXTVAL FROM dual", keyProperty = "id", before = true, resultType = Long.class)
    void insertReview(Review review);

    // 4. 리뷰 수정
    @Update("UPDATE reviews SET content = #{content}, rating = #{rating} WHERE id = #{id}")
    void updateReview(Review review);

    // 5. 리뷰 삭제
    @Delete("DELETE FROM reviews WHERE id = #{id}")
    void deleteReview(Long id);

    // (옵션) 특정 장소(placeId)의 모든 리뷰 조회
    @Select("SELECT * FROM reviews WHERE place_id = #{placeId}")
    List<Review> findByPlaceId(Long placeId);

}
