package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.FavoritePlace;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoritePlaceMapper {

    // 1. 즐겨찾기 등록 (placeId 추가)
    @Insert("INSERT INTO FAVORITE_PLACES (ID, USER_ID, PLACE_ID, NAME, ADDRESS, LIKED) " +
            "VALUES (#{id}, #{userId}, #{placeId}, #{name}, #{address}, #{liked})")
    @SelectKey(statement = "SELECT user_seq.NEXTVAL FROM dual", keyProperty = "id", before = true, resultType = Long.class)
    void insertFavorite(FavoritePlace favoritePlace);

    // 2. 특정 유저의 찜 목록 조회
    @Select("SELECT * FROM favorite_places WHERE user_id = #{userId}")
    List<FavoritePlace> findByUserId(Long userId);

    // 3. id로 삭제
    @Delete("DELETE FROM favorite_places WHERE id = #{id}")
    void deleteFavoriteById(Long id);

    // ✅ 4. (userId, placeId) 기준으로 중복 확인
    @Select("SELECT * FROM favorite_places WHERE user_id = #{userId} AND place_id = #{placeId}")
    FavoritePlace findByUserIdAndPlaceId(@Param("userId") Long userId,
                                         @Param("placeId") Long placeId);

    // ✅ 5. liked 상태 토글
    @Update("UPDATE favorite_places SET liked = CASE WHEN liked = 'Y' THEN 'N' ELSE 'Y' END WHERE id = #{id}")
    void toggleLikedById(Long id);

    // (선택) liked 상태를 명시적으로 설정하고 싶을 때
    @Update("UPDATE FAVORITE_PLACES SET LIKED = #{liked} WHERE ID = #{id}")
    void setLikedById(@Param("id") Long id, @Param("liked") String liked);
}
