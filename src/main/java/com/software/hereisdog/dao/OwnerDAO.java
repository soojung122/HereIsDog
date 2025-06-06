package com.software.hereisdog.dao;

import com.software.hereisdog.domain.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface OwnerDAO {

    @Select("SELECT * FROM PLACE WHERE OWNER_USERNAME = #{ownerUsername}")
    List<Place> findPlacesByOwnerUsername(String ownerUsername);

}