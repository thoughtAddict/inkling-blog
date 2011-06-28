package com.thoughtaddict.mongoblog.javabean;

public class BlogArchive {

	private static final long serialVersionUID = 1002L;
	
	private String archiveName;
	private int numberPosts;
	private int archiveOrder;
	private String archiveMonth;
	private String archiveYear;
	
	public String getArchiveName() {
		return archiveName;
	}
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	public int getNumberPosts() {
		return numberPosts;
	}
	public void setNumberPosts(int numberPosts) {
		this.numberPosts = numberPosts;
	}
	public int getArchiveOrder() {
		return archiveOrder;
	}
	public void setArchiveOrder(int archiveOrder) {
		this.archiveOrder = archiveOrder;
	}
	public String getArchiveMonth() {
		return archiveMonth;
	}
	public void setArchiveMonth(String archiveMonth) {
		this.archiveMonth = archiveMonth;
	}
	public String getArchiveYear() {
		return archiveYear;
	}
	public void setArchiveYear(String archiveYear) {
		this.archiveYear = archiveYear;
	}

}