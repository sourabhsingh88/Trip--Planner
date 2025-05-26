package com.amstech.tripplanner.booking.repo.custom.impl;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.User;
import com.amstech.tripplanner.booking.repo.custom.UserCustomRepo;
import com.mysql.cj.log.Log;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserCriteriaImplRepo implements UserCustomRepo{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> filterBy(Integer page, Integer size, String phoneNumber, Integer locationId, String gender,
			Long dobStartDate, Long dobEndDate, List<Integer> roleIds, Integer status, String keyword)
			throws Exception {
		log.info("Implement Criteria Query");
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = userCriteriaQuery.from(User.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if(phoneNumber != null)
			predicates.add(criteriaBuilder.equal(userRoot.get("phoneNumber"), phoneNumber));
		
		if(gender !=null)
			predicates.add(criteriaBuilder.equal(userRoot.get("gender"), gender));
		
		if(status !=null)
			predicates.add(criteriaBuilder.equal(userRoot.get("isDeleted"), status));
		
		if(dobStartDate != null && dobStartDate != null)
			predicates.add(criteriaBuilder.between(userRoot.get("dob"), dobStartDate,dobStartDate));
		
		if(keyword != null) {
			List<Predicate> keyWordPredicates =  new ArrayList<>();
			keyWordPredicates.add(criteriaBuilder.like(userRoot.get("name"), "%"+ keyword + "%"));
			keyWordPredicates.add(criteriaBuilder.like(userRoot.get("phoneNumber"), "%"+ keyword + "%"));
			keyWordPredicates.add(criteriaBuilder.like(userRoot.get("email"), "%"+ keyword + "%"));
			keyWordPredicates.add(criteriaBuilder.like(userRoot.get("gender"), "%"+ keyword + "%"));
			
			predicates.add(criteriaBuilder.or(keyWordPredicates.toArray( new Predicate[keyWordPredicates.size()])));
		}
		
		Predicate finalPredicate = criteriaBuilder.and(predicates.toArray( new Predicate[predicates.size()]));
		userCriteriaQuery.where(finalPredicate);
		
		return entityManager.createQuery(userCriteriaQuery).getResultList();
	}

	@Override
	public long countBy(String phoneNumber, Integer locationId, String gender, Long dobStartDate, Long dobEndDate,
			List<Integer> roleIds, Integer status, String keyword) throws Exception {
		return 0;
	}

}
