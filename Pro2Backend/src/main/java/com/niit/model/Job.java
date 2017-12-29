package com.niit.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String jobtitle;
	private String jobDescription;
	private String skillsrequired;
	private String location;
	private String companyname;
	private String salary;
	private String yrsOfExp;
	private Date postedOn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getSkillsrequired() {
		return skillsrequired;
	}
	public void setSkillsrequired(String skillsrequired) {
		this.skillsrequired = skillsrequired;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getYrsOfExp() {
		return yrsOfExp;
	}
	public void setYrsOfExp(String yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	
}