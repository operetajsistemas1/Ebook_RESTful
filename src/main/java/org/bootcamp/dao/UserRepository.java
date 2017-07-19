package org.bootcamp.dao;

import org.bootcamp.AWS.Book;
import org.bootcamp.AWS.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.name = :name")
    Stream<User> findUserByName(@Param("name") String name);
    @Query("select u from User u where u.id = :id")
    Stream<User> findUserById(@Param("id") int id);

//    @Query("select u from User u")
//    Stream<User> findAllAndStream();

    @Query("select u from User u where u.id = :id")
    User findOne(@Param("id") int id);
    
//    @Modifying
//    User save(@Param("user") User user);

	@Modifying
    @Query("delete from User u where u.id = :id")    
	void delete(@Param("id") int id);
}
