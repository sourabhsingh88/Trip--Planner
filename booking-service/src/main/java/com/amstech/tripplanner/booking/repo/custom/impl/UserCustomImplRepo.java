package com.amstech.tripplanner.booking.repo.custom.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.repo.custom.UserCustomRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserCustomImplRepo implements UserCustomRepo{

	@Autowired
	private EntityManager entityManager;
	@Override
	public List<User> filterBy(Integer page, Integer size, String phoneNumber, Integer locationId, String gender,
			Long dobStartDate, Long dobEndDate, List<Integer> roleIds, Integer status, String keyword)
			throws Exception {
		
		String query = createQuery(" e ", phoneNumber, locationId, gender, dobStartDate, dobEndDate, roleIds, status, keyword);
		log.info("Query : {} " + query);
		TypedQuery<User> typedQuery = entityManager.createQuery(query,User.class);
		setParam(typedQuery, page, size, phoneNumber, locationId, gender, dobStartDate, dobEndDate, roleIds, status, keyword);
		return typedQuery.getResultList();
	}

	@Override
	public long countBy(String phoneNumber, Integer locationId, String gender, Long dobStartDate, Long dobEndDate,
			List<Integer> roleIds, Integer status, String keyword) throws Exception {
		String query = createQuery(" count(e) ", phoneNumber, locationId, gender, dobStartDate, dobEndDate, roleIds, status, keyword);
		log.info("Query : {} " + query);
		TypedQuery<Long> typedQuery = entityManager.createQuery(query,Long.class);
		setParam(typedQuery, null, null, phoneNumber, locationId, gender, dobStartDate, dobEndDate, roleIds, status, keyword);
		return typedQuery.getSingleResult();
	}
	
	
	public String createQuery(String type, String phoneNumber, Integer locationId, String gender, Long dobStartDate, Long dobEndDate,
			List<Integer> roleIds, Integer status, String keyword) {
		
		StringBuilder query = new StringBuilder("select " + type + " from User e ");
		
		if(roleIds != null) { 
			query.append(" Join UserRole ur on e.id = ur.user.id ");
		}
		boolean isWhereAdded = false;
		
		if(phoneNumber != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.phoneNumber=:phoneNumber ");
		}
		if(locationId != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.location.id=:locationId ");
		}
		if(gender != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.gender=:gender ");
		}
		if(dobStartDate != null && dobEndDate != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.dob between :dobStartDate and :dobEndDate ");
		}
		if(status != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.isDeleted=:status ");
		}
		if(keyword != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.name like :keyword OR e.email like :keyword OR e.phoneNumber like :keyword OR e.gender like :keyword ");
		}
		if (roleIds != null) {
			if (!isWhereAdded) {
				query.append("where");
				isWhereAdded = true;
			} else {
				query.append("and");
			}
			query.append(" ur.role.id in :roleIds ");
		}
		
		return query.toString();
	}
	
	public void setParam(Query query,Integer page, Integer size, String phoneNumber, Integer locationId, String gender, Long dobStartDate, Long dobEndDate,
			List<Integer> roleIds, Integer status, String keyword) {

		
		if(phoneNumber != null) {
			query.setParameter("phoneNumber", phoneNumber);
		}
		
		if(gender != null) {
			query.setParameter("gender", gender);
		}
		
		if(dobStartDate != null && dobEndDate != null) {
			query.setParameter("dobStartDate", dobStartDate);
			query.setParameter("dobEndDate", dobEndDate);
		}
		
		if(status != null) {
			query.setParameter("status", status);
		}
		
		if(keyword != null) {
			query.setParameter("keyword", "%" + keyword + "%");
		}
		
		if (roleIds != null) {
			query.setParameter("roleIds", roleIds);
		}

		if (page != null && size != null) {
			query.setMaxResults(size);
			query.setFirstResult(page*size);
		}
		if(locationId != null) {
			query.setParameter("locationId",locationId);
		}

		
	}

}
