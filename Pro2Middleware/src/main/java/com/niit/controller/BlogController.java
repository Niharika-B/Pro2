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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.BlogPostDao;

import com.niit.Dao.UserDao;

import com.niit.model.Blog;

import com.niit.model.ErrorClazz;
import com.niit.model.User;

	@RestController
	public class BlogController {

		@Autowired
		private BlogPostDao blogPost;
		@Autowired
		private UserDao userDao;

		@RequestMapping(value = "/saveblog", method = RequestMethod.POST)
		public ResponseEntity<?> savejob(@RequestBody Blog blog, HttpSession session) {

			if (session.getAttribute("username") == null) {
				ErrorClazz error = new ErrorClazz(5, "UnAuthorized User");
				return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
			}

			String username = (String) session.getAttribute("username");
		
			User user = userDao.getUserByUsername(username);
			blog.setPostedBy(user);
			blog.setPostedOn(new Date());
			try {
				blogPost.saveblog(blog);

			} catch (Exception e) {
				ErrorClazz error = new ErrorClazz(6, "Unable to insert blog post details " + e.getMessage());
				return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
	}

