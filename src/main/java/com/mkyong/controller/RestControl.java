package com.mkyong.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.dao.CustomerRepository;
import com.mkyong.model.User;


@RestController
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RestControl {
	@Autowired
	DataSource dataSource;

	@Autowired
	CustomerRepository customerRepository;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @RequestMapping("/searchByName")
    public List<User> findUser(@RequestParam(value="name") String name) {
    	System.out.println("searchByName " +  name);
    	ArrayList<User> users = new ArrayList<User>();
		try (Stream<User> stream = customerRepository.findByEmailReturnStream(name)) {
		stream.forEach(x -> users.add(x));
	//	stream.forEach(x -> System.out.println(x));
		}
    	System.out.println(users.toString());
    	return users;
    }
	
	@Modifying
    @RequestMapping("/delete")
    public void delete(@RequestParam(value="id") int id) {
    //	System.out.println("deletebyid " +  id);
    //	User user = new User();
  //  	user = customerRepository.findOne(id);
		
		customerRepository.delete(id);

    }
	
	@Modifying
    @RequestMapping("/insert")
    public User save(@RequestParam(value="user") User user) {
    //	System.out.println("deletebyid " +  id);
    	User user2 = new User();
  //  	user = customerRepository.findOne(id);
		
		user2 = customerRepository.save(user);
		return user2;
    }
	
	
    
    
    
}