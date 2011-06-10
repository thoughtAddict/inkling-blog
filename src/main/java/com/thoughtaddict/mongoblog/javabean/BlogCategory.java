package com.thoughtaddict.mongoblog.javabean;

import java.io.Serializable;

public class BlogCategory implements Serializable {

	private static final long serialVersionUID = 1001L;
	
	private String categoryName;
	private int numberPosts;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getNumberPosts() {
		return numberPosts;
	}

	public void setNumberPosts(int numberPosts) {
		this.numberPosts = numberPosts;
	}

}