package org.bootcamp.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.bootcamp.AWS.User;
import org.bootcamp.AWS.Book;
import org.bootcamp.dao.BookRepository;
import org.bootcamp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RestControl {

	private final UserRepository userRepository;

	private final BookRepository bookRepository;		


	@Autowired
	DataSource dataSource;
	@Autowired
	RestControl(UserRepository userRepository, BookRepository bookRepository) {
					this.userRepository = userRepository;
					this.bookRepository = bookRepository;
	}


	@RequestMapping("/searchBook")
	public List<Book> findBook(@RequestParam(value="name") String name) {
		System.out.println("searchBook " +  name);
		ArrayList<Book> book = new ArrayList<Book>();
		try (Stream<Book> stream = bookRepository.findBookByName(name)) {
				stream.forEach(x -> book.add(x));
		}
		System.out.println(book.toString());
		return book;
	}	

	@RequestMapping("/searchUser")
	public List<User> findUser(@RequestParam(value="name") String name) {
		System.out.println("searchUawe " +  name);
		List<User> users = new ArrayList<User>();
		try (Stream<User> stream = userRepository.findUserByName(name)) {
				stream.forEach(x -> users.add(x));
		}
		System.out.println(users.toString());
		return users;
	}

	@Modifying
	@RequestMapping("/deleteUser")
	public void deleteUser(@RequestParam(value="id") int id) {
		userRepository.delete(id);
	}
	@Modifying
	@RequestMapping("/deleteBook")
	public void deleteBook(@RequestParam(value="id") int id) {
		bookRepository.delete(id);
	}

	@Modifying
	@RequestMapping("/insertUser")
	public User save(@RequestParam(value="name") String str) throws IOException {
		System.out.println(str);
		User user = new User(str, "dummy");
		user.setBookId(0);
		System.out.println(user.toString());
		return  userRepository.save(user);
	}
	
	@Modifying
	@RequestMapping("/insertBook")
	public Book save(@RequestParam(name="name") String str, @RequestParam(value="user") int id) {

		Book book = new Book(str);
		System.out.println(book.toString());
		User user = userRepository.findOne(id);
		System.out.println(user.toString());
		book.setUser(user);
		//	user = objectyify(str);
		System.out.println(book.toString());
		return  bookRepository.save(book);
	}
	
	@Modifying
	@RequestMapping("/updateBook")
	public Book updateBook(@RequestParam(value="line") int line, 
						@RequestParam(value="user") int id,
						@RequestParam(name="name") String name,
						@RequestParam(value="id") int bookid) {

		Book book = new Book(name);
		book.setLine(line);
		System.out.println(book.toString());
		User user = userRepository.findOne(id);
		System.out.println(user.toString());
		book.setUser(user);
		book.setId(bookid);
		//	user = objectyify(str);
		System.out.println(book.toString());
		return  bookRepository.save(book);
	}
	
	@Modifying
	@RequestMapping("/updateUser")
	public User updateUser(@RequestParam(value="book") int bookid, 
						@RequestParam(value="user") int id,
						@RequestParam(name="name") String name) {

		User user = new User(name, "dummy");
		user.setId(id);
		user.setBookId(bookid);

		return  userRepository.save(user);
	}

}