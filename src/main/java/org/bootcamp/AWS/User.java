package org.bootcamp.AWS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.FetchType;
import javax.persistence.*;




@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 4674769194392810510L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToMany(mappedBy = "User")
	@OrderBy("id ASC")
	private Set<Book> books;
	
//	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="book_id")
//	@Transient
	private int book_id;
	
	public int getBookId() {
		return book_id;
	}

	public void setBookId(int book_id) {
		this.book_id = book_id;
	}

	private  String name;
	private String password;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public User( String name, String password) {
		super();
		this.name = name;
		this.password = password;
		books = new HashSet<Book>();	
	}


	public User() {
		super();
		books = new HashSet<Book>();
	}
	
	public void addBook(Book book) {
		// 
		System.out.println("Invoice add item2: " + book.toString());
		books.add(book);
		System.out.println("Invoice content " + this.toString());		
		
	}

	public Set<Book> getItems() {
		
		return this.books;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", books=" + books + ", book_id=" + book_id + ", name=" + name + ", password="
				+ password + "]";
	}
	
	
	
	
	
}

