package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.FavoritePlace;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoritePlaceMapper {

    // 즐겨찾기 등록
    @Insert("INSERT INTO FAVORITE_PLACES (ID, USER_ID, NAME, ADDRESS, LIKED) " +
            "VALUES (#{id}, #{userId}, #{name}, #{address}, #{liked})")
    @SelectKey(statement = "SELECT user_seq.NEXTVAL FROM dual", keyProperty = "id", before = true, resultType = Long.class)
    void insertFavorite(FavoritePlace favoritePlace);
    
    // userid
    @Select("SELECT * FROM favorite_places WHERE user_id = #{userId}")
    List<FavoritePlace> findByUserId(Long userId);

    @Delete("DELETE FROM favorite_places WHERE user_id = #{userId} AND name = #{name} AND address = #{address}")
    void deleteFavorite(@Param("userId") Long userId,
                        @Param("name") String name,
                        @Param("address") String address);
    
    // 찜 중복 - id, name, address로 확인
    @Select("SELECT * FROM favorite_places WHERE user_id = #{userId} AND name = #{name} AND address = #{address}")
    FavoritePlace findByUserIdAndPlace(@Param("userId") Long userId,
                                       @Param("name") String name,
                                       @Param("address") String address);

    // liked 상태 토글
    @Update("UPDATE favorite_places SET liked = CASE WHEN liked = 'Y' THEN 'N' ELSE 'Y' END WHERE id = #{id}")
    void toggleLikedById(Long id);

    // (선택) liked 상태를 명시적으로 설정하고 싶을 때
    @Update("UPDATE FAVORITE_PLACES SET LIKED = #{liked} WHERE ID = #{id}")
    void setLikedById(@Param("id") Long id, @Param("liked") String liked);
}
