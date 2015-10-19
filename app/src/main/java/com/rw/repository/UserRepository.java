package com.rw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rw.entity.User;


public interface UserRepository extends JpaRepository<User, Long>  {

	User findByPhoneNumber(@Param("phone") String phone);
	
	//@Query("select u from #{#entityName} u where u.lastname = ?1")
	//List<User> findByLastname(String lastname);
}
