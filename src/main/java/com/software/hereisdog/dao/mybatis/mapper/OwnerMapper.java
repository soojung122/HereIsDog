package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Place;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OwnerMapper {

	@Select("SELECT * FROM place WHERE owner_username = #{ownerUsername}")
	@Results({
	    @Result(property = "id", column = "id"),
	    @Result(property = "name", column = "name"),
	    @Result(property = "address", column = "address"),
	    @Result(property = "description", column = "description"),
	    @Result(property = "ownerUsername", column = "owner_username"),
	    @Result(property = "openingHours", column = "opening_hours"),
	    @Result(property = "phoneNumber", column = "phone_number"),
	    @Result(property = "type", column = "type")
	})
	Place selectPlaceByOwner(String ownerUsername);


	@Update("""
		    UPDATE place
		    SET name = #{name},
		        opening_hours = #{openingHours},
		        phone_number = #{phoneNumber}
		    WHERE owner_username = #{ownerUsername}
		""")
		void updatePlace(Place place);

	
    @Select("SELECT * FROM place WHERE owner_username = #{ownerUsername}")
    List<Place> selectPlacesByOwner(String ownerUsername); // 여러 가게 조회용
}
