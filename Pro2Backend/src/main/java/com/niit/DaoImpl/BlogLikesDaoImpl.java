package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.BlogLikesDao;
import com.niit.model.Blog;
import com.niit.model.BlogLikes;
import com.niit.model.User;


@Repository
@Transactional
public class BlogLikesDaoImpl implements BlogLikesDao{
	
	@Autowired
	private SessionFactory sessionF;

	public BlogLikesDao userlikes(Blog blog, User user) {
		Session session=sessionF.getCurrentSession();
		Query query=session.createQuery("from BlogLikes where blog.id=? and user.username=? ");
		System.out.println("Blog Id : "+ blog.getId());
		System.out.println("username : "+user.getUsername());
		query.setInteger(0, blog.getId());
		query.setString(1, user.getUsername());
		BlogLikesDao bloglikes=(BlogLikesDao)query.uniqueResult();
		System.out.println(bloglikes);
		return bloglikes;
	}

	public Blog updatelikes(Blog blog, User user) {
		Session session=sessionF.getCurrentSession();
		BlogLikesDao bloglikes=userlikes(blog,user);
		if(bloglikes==null){
			BlogLikes insertlikes=new BlogLikes();
			insertlikes.setBlog(blog);
			insertlikes.setUser(user);
			session.save(insertlikes);
			session.update(blog);
		}else{
			session.delete(bloglikes);
			blog.setLikes(blog.getLikes()-1);
			session.merge(blog);
		}
		return blog;
	}

	public void setBlog(Blog blog) {
		// TODO Auto-generated method stub
		
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
	 
}