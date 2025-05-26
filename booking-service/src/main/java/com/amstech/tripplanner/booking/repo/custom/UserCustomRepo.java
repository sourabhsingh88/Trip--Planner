package com.amstech.tripplanner.booking.repo.custom;

import java.util.List;

import com.amstech.tripplanner.booking.entity.User;


public interface UserCustomRepo {

	public List<User> filterBy(Integer page, Integer size, String phoneNumber, Integer locationId, String gender, Long dobStartDate, Long dobEndDate, List<Integer> roleIds, Integer status, String keyword) throws Exception;
	public long countBy(String phoneNumber, Integer locationId, String gender, Long dobStartDate, Long dobEndDate, List<Integer> roleIds, Integer status, String keyword) throws Exception;

}
