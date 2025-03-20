package com.amstech.tripplanner.booking.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.amstech.tripplanner.booking.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

	@Query("select ur from UserRole ur where ur.user.id =:userId and ur.role.id =:roleId")
	UserRole findByUserIdRoleId( @Param("userId") int userId,@Param("roleId") int roleId);
	
	@Query("select ur from UserRole ur where ur.role.id =:roleId")
	List<UserRole> findByRoleId(@Param("roleId") int roleId, Pageable pageable);
	
	@Query("select count(ur) from UserRole ur where ur.role.id =:roleId")
	long countByRoleId(@Param("roleId") int roleId);
	
	@Query("select ur from UserRole ur where ur.user.id =:userId")
	List<UserRole> findByUserId( @Param("userId") int userId, Pageable pageable);
	
	@Query("select count(ur) from UserRole ur where ur.user.id =:userId")
	long countByUserId( @Param("userId") int userId);
	
	@Query("select ur from UserRole ur")
	List<UserRole> findAllUserRole(Pageable pageable);
	
	@Query("select count(ur) from UserRole ur")
	long countAllUserRole();

}
