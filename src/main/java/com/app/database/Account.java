package com.app.database;

import java.io.Serializable;

public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String isbn,title,publisher,author,thumbnail;

	public Account(String isbn, String title, String publisher, String author, String thumbnail) {
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.thumbnail = thumbnail;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public void info() {
		System.out.printf("%s %s %s %s %s\n",isbn,title,publisher,author,thumbnail);
	}
	
	
}
