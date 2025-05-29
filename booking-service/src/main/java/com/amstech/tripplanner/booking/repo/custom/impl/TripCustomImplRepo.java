package com.amstech.tripplanner.booking.repo.custom.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Trip;
import com.amstech.tripplanner.booking.repo.custom.TripCustomRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TripCustomImplRepo implements TripCustomRepo{

	@Autowired
	private EntityManager entityManager;
	@Override
	public List<Trip> filterBy(Integer page, Integer size, String to, String from, Integer duration, Integer price,  String keyword)
			throws Exception {
		
		String query = createQuery(" e ", to, from, duration, price, keyword);
		log.info("Query : {} " + query);
		TypedQuery<Trip> typedQuery = entityManager.createQuery(query,Trip.class);
		setParam(typedQuery, page, size, to, from, duration, price, keyword);
		return typedQuery.getResultList();
	}

	@Override
	public long countBy(String to, String from, Integer duration, Integer price,  String keyword) throws Exception {
		String query = createQuery(" count(e) ", to, from, duration, price,keyword);
		log.info("Query : {} " + query);
		TypedQuery<Long> typedQuery = entityManager.createQuery(query,Long.class);
		setParam(typedQuery, null, null,  to, from, duration, price, keyword);
		return typedQuery.getSingleResult();
	}
	
	
	public String createQuery(String type, String to, String from, Integer duration, Integer price,  String keyword) {
		
		StringBuilder query = new StringBuilder("select " + type + " from Trip e ");
		
		
		boolean isWhereAdded = false;
		
		if(to != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.toLocation=:to ");
		}
		if(from != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.fromLocation=:from ");
		}
		if(duration != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.duration=:duration ");
		}
		
		if(price != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.price=:price ");
		}
		if(keyword != null) {
			if(!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			}else {
				query.append(" and ");
			}
			query.append(" e.name like :keyword OR e.toLocation like :keyword OR e.fromLocation like :keyword ");
		}
		
		return query.toString();
	}
	
	public void setParam(Query query,Integer page, Integer size, String to, String from, Integer duration, Integer price,  String keyword) {

		
		if(to != null) {
			query.setParameter("to", to);
		}
		
		if(from != null) {
			query.setParameter("from", from);
		}
		
		
		
		if(duration != null) {
			query.setParameter("duration", duration);
		}
		
		if(keyword != null) {
			query.setParameter("keyword", "%" + keyword + "%");
		}
		
		if (price != null) {
			query.setParameter("price", price);
		}

		if (page != null && size != null) {
			query.setMaxResults(size);
			query.setFirstResult(page*size);
		}
		

		
	}

}
