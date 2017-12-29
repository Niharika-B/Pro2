package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.JobDao;
import com.niit.Dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Job;
import com.niit.model.User;

@Controller
public class JobController {
	@Autowired
private JobDao jobDao;
	@Autowired
private UserDao userDao;
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		
		User user=userDao.getUserByUsername(username);
        
		if(user.getRole().equals("ADMIN")){
        	try{
        		job.setPostedOn(new Date());
        	jobDao.saveJob(job);
        	return new ResponseEntity<Job>(job,HttpStatus.OK);
        	}catch(Exception e){
        		ErrorClazz error=new ErrorClazz(7,"Unable to insert job details " + e.getMessage());
        		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }
        else{
        	ErrorClazz error=new ErrorClazz(6,"Access Denied");
        	return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
        }
		
	}
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	@RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id,HttpSession session){
		
		if(session.getAttribute("username")==null){
			ErrorClazz error=new ErrorClazz(5,"UnAuthroized User");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job=jobDao.getJobById(id);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		
	}
	
}