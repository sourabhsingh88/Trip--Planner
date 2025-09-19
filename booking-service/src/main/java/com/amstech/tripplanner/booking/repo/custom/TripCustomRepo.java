package com.amstech.tripplanner.booking.repo.custom;

import java.util.List;

import com.amstech.tripplanner.booking.entity.Trip;

public interface TripCustomRepo {

	public List<Trip> filterBy(Integer page, Integer size, String to, String from, Integer startDuration,Integer endDuration, Integer startPrice,Integer endPrice,  String keyword) throws Exception;
	public long countBy( String to, String from, Integer startDuration,Integer endDuration,  Integer startPrice,Integer endPrice,  String keyword) throws Exception;

}
