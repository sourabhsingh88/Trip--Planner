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
public class TripCustomImplRepo implements TripCustomRepo {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Trip> filterBy(Integer page, Integer size, String to, String from, Integer startDuration,
			Integer endDuration, Integer startPrice, Integer endPrice, String keyword) throws Exception {

		String query = createQuery(" e ", to, from, startDuration, endDuration, startPrice, endPrice, keyword);
		log.info("Query : {} " + query);
		TypedQuery<Trip> typedQuery = entityManager.createQuery(query, Trip.class);
		setParam(typedQuery, page, size, to, from, startDuration, endDuration, startPrice, endPrice, keyword);
		return typedQuery.getResultList();
	}

	@Override
	public long countBy(String to, String from, Integer startDuration, Integer endDuration, Integer startPrice,
			Integer endPrice, String keyword) throws Exception {
		String query = createQuery(" count(e) ", to, from, startDuration, endDuration, startPrice, endPrice, keyword);
		log.info("Query : {} " + query);
		TypedQuery<Long> typedQuery = entityManager.createQuery(query, Long.class);
		setParam(typedQuery, null, null, to, from, startDuration, endDuration, startPrice, endPrice, keyword);
		return typedQuery.getSingleResult();
	}

	public String createQuery(String type, String to, String from, Integer startDuration, Integer endDuration,
			Integer startPrice, Integer endPrice, String keyword) {

		StringBuilder query = new StringBuilder("select " + type + " from Trip e ");

		boolean isWhereAdded = false;

		if (to != null) {
			if (!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			} else {
				query.append(" and ");
			}
			query.append(" e.toLocation=:to ");
		}
		if (from != null) {
			if (!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			} else {
				query.append(" and ");
			}
			query.append(" e.fromLocation=:from ");
		}
		if (startDuration != null && endDuration != null) {
			if (!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			} else {
				query.append(" and ");
			}
			query.append(" e.duration between :startDuration and :endDuration ");
		}

		if (startPrice != null && endPrice != null) {
			if (!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			} else {
				query.append(" and ");
			}
			query.append(" e.price between :startPrice and :endPrice ");
		}
		if (keyword != null) {
			if (!isWhereAdded) {
				query.append(" where ");
				isWhereAdded = true;
			} else {
				query.append(" and ");
			}
			query.append(" e.name like :keyword OR e.toLocation like :keyword OR e.fromLocation like :keyword ");
		}

		return query.toString();
	}

	public void setParam(Query query, Integer page, Integer size, String to, String from, Integer startDuration,
			Integer endDuration, Integer startPrice, Integer endPrice, String keyword) {

		if (to != null) {
			query.setParameter("to", to);
		}

		if (from != null) {
			query.setParameter("from", from);
		}

		if (startDuration != null && endDuration != null) {
			query.setParameter("startDuration", startDuration);
			query.setParameter("endDuration", endDuration);
		}

		if (keyword != null) {
			query.setParameter("keyword", "%" + keyword + "%");
		}

		if (startPrice != null && endPrice != null) {
			query.setParameter("startPrice", startPrice);
			query.setParameter("endPrice", endPrice);
		}

		if (page != null && size != null) {
			query.setMaxResults(size);
			query.setFirstResult(page * size);
		}

	}

}
