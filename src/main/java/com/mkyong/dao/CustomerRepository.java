package com.mkyong.dao;

import com.mkyong.model.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface CustomerRepository extends CrudRepository<User, Long> {
//
 //   List<User> findByEmail(String email);
//		List<User> findByName(String name);
    @Query("select u from User u where u.name = :name")
    Stream<User> findByEmailReturnStream(@Param("name") String name);

 //   List<Customer> findByDate(Date date);

    @Query("select u from User u")
    Stream<User> findAllAndStream();

    @Modifying
    User save(@Param("user") User user);
    //List<Customer> findByDateBetween(Date from, Date to);
//    @Query("select u from User u where u.id = :id")    
//	User findOne(@Param("id") int id);
	@Modifying
    @Query("delete from User u where u.id = :id")    
	void delete(@Param("id") int id);
}
