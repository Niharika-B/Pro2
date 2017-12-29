package com.niit.DaoImpl;

import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogPostDao;
import com.niit.model.Blog;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

	@Repository
	@Transactional
	public class BlogPostDaoImpl implements BlogPostDao {

		@Autowired
		private SessionFactory sessionFactory;
		public void saveblog(Blog blog) {
			Session session=sessionFactory.getCurrentSession();
			session.save(blog);
			
		}
	}

