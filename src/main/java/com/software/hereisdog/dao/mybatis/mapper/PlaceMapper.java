package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Place;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PlaceMapper {
	
    @Select("SELECT * FROM place")
    List<Place> findAll();

    @Select("SELECT * FROM place WHERE type = #{type}")
    List<Place> findByType(String type);

    @Select("SELECT * FROM place WHERE owner_username = #{ownerUsername}")
    Place selectPlaceByOwner(String ownerUsername);
    
    @Select("SELECT * FROM place WHERE address = #{address}")
    @Results({
        @Result(property = "name", column = "name"),
        @Result(property = "address", column = "address"),
        @Result(property = "description", column = "description"),
        @Result(property = "ownerUsername", column = "owner_username"),
        @Result(property = "openingHours", column = "opening_hours"),  
        @Result(property = "phoneNumber", column = "phone_number")    
    })
    Place selectPlaceByAddress(String address);
    
    @Select("SELECT * FROM place WHERE name = #{name} AND address = #{address}")
    Place findByNameAndAddress(@Param("name") String name, @Param("address") String address);

    @Insert("INSERT INTO place (id, name, address, description, owner_username, opening_hours, phone_number) " +
            "VALUES (PLACE_SEQ.NEXTVAL, #{name}, #{address}, #{description}, #{ownerUsername}, #{openingHours}, #{phoneNumber})")
    void insert(Place place);


}
