package com.software.hereisdog.dao.mybatis.mapper;

import com.software.hereisdog.domain.Place;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PlaceMapper {

    @Insert("INSERT INTO place (name, address, description, owner_username, opening_hours, phone_number) " +
            "VALUES (#{name}, #{address}, #{description}, #{ownerUsername}, #{openingHours}, #{phoneNumber})")
    void insertPlace(Place place);  
    
    @Select("SELECT * FROM place")
    List<Place> findAll();

    @Select("SELECT * FROM place WHERE type = #{type}")
    List<Place> findByType(String type);

    @Update("UPDATE place SET name = #{name}, address = #{address}, description = #{description}, " +
            "opening_hours = #{openingHours}, phone_number = #{phoneNumber} " +
            "WHERE owner_username = #{ownerUsername}")
    void updatePlace(Place place);

    @Select("SELECT * FROM place WHERE owner_username = #{ownerUsername}")
    Place selectPlaceByOwner(String ownerUsername);
    
    @Select("SELECT * FROM place WHERE address = #{address}")
    Place selectPlaceByAddress(String address);

}
