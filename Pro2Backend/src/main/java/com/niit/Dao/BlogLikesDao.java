package com.niit.Dao;

import com.niit.model.Blog;
import com.niit.model.User;

public interface BlogLikesDao {
		
		BlogLikesDao userlikes(Blog blog,User user);
		Blog updatelikes(Blog blog,User user);
		void setBlog(Blog blog);
		void setUser(User user);
		
		  

	}

