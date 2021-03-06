package com.thoughtaddict.mongoblog.javabean;

import java.io.Serializable;
import java.util.*;


public class BlogPost implements Serializable {

	private static final long serialVersionUID = 1000L;
	
	private String postId;
	private String author;
	private String dateCreated;
	private String title;
	private String content;
	private List<String> categories = new ArrayList<String>();
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public void addCategories(String category) {
		this.categories.add(category);
	}
}