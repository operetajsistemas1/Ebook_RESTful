package org.bootcamp.dao;

import java.util.stream.Stream;

import org.bootcamp.AWS.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("select b from Book b where b.name = :name")
    Stream<Book> findBookByName(@Param("name") String name);
    @Query("select b from Book b where b.id = :id")
    Stream<Book> findBookById(@Param("id") int id);

//    @Query("select u from User u")
//    Stream<Book> findAllAndStream();
//
    @Modifying
    Book save(@Param("book") Book book);
    
//	@Modifying
//    @Query("delete from Book b where b.id = :id")    
//	void delete(@Param("id") int id);
    
//
	@Modifying
    @Query("delete from User u where u.id = :id")    
	void delete(@Param("id") int id);
	
	
	
}