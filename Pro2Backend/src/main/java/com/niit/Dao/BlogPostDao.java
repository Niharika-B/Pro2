package com.niit.Dao;
import com.niit.model.Blog;
import com.niit.model.BlogComment;

import java.util.List;
public interface BlogPostDao {

		void saveblog(Blog blog);
		List<Blog> getBlogs(int approved);
		Blog getbyBlogId(int id);
		void updateBlogPost(Blog blog, String rejectionReason);
		
		void addcomment(BlogComment blogcomment);
	}
	

