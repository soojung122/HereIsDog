package com.software.hereisdog.dao;

import java.util.List;
import com.software.hereisdog.domain.Place;

public interface PlaceDao {
	List<Place> findAll();  
	List<Place> findByType(String type);
}
