package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.amstech.tripplanner.booking.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

	@Query("select ur from UserRole ur where ur.user.id =:userId and ur.role.id =:roleId")
	UserRole findByUserIdRoleId( @Param("userId") int userId,@Param("roleId") int roleId);
}
