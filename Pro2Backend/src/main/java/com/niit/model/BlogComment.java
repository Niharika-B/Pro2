package com.niit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class BlogComment {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JsonIgnore
	private Blog blog;
	@ManyToOne
	private User commentedBy;
	private Date commentedOn;
	@Column(nullable=false)
	private String commentedText;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public User getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}
	public Date getCommentedOn() {
		return commentedOn;
	}
	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}
	public String getCommentedText() {
		return commentedText;
	}
	public void setCommentedText(String commentedText) {
		this.commentedText = commentedText;
	}

	 		
}