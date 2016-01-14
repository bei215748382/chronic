package com.mlnx.chronic.entity;

import java.util.Date;

public class TReport {
	private Integer id;

	private String title;

	private String body;

	private String author;

	private Date time;

	private String suggest;

	private Integer userId;

	public TReport() {

	}

	public TReport(String title, String body, String author, String suggest) {
		this.title = title;
		this.body = body;
		this.author = author;
		this.suggest = suggest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TReport [id=" + id + ", title=" + title + ", body=" + body
				+ ", author=" + author + ", time=" + time + ", suggest="
				+ suggest + ", userId=" + userId + "]";
	}

}