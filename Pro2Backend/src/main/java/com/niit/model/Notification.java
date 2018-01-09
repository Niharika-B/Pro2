package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Notification {
@Id
@GeneratedValue

private String username;
private String blogtitle;
private String approvalstatus;
private String rejectedreason;
private boolean viewed;



private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getBlogtitle() {
	return blogtitle;
}
public void setBlogtitle(String blogtitle) {
	this.blogtitle = blogtitle;
}
public String getApprovalstatus() {
	return approvalstatus;
}
public void setApprovalstatus(String approvalstatus) {
	this.approvalstatus = approvalstatus;
}
public String getRejectedreason() {
	return rejectedreason;
}
public void setRejectedreason(String rejectedreason) {
	this.rejectedreason = rejectedreason;
}
public boolean isViewed() {
	return viewed;
}
public void setViewed(boolean viewed) {
	this.viewed = viewed;
}

}