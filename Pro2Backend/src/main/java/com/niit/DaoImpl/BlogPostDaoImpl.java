package com.niit.DaoImpl;

import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import com.niit.Dao.BlogPostDao;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Notification;

import java.util.List;

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
		public List<Blog> getBlogs(int approved) {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Blog where approved=" + approved);
			return query.list();
		}

		public Blog getbyBlogId(int id) {
			Session session = sessionFactory.getCurrentSession();
			Blog blog = (Blog) session.get(Blog.class, id);
			return blog;
		}
		public void updateBlogPost(Blog blog, String rejectedReason) {
			Session session = sessionFactory.getCurrentSession();
			Notification notification = new Notification();
			notification.setBlogtitle(blog.getBlogtitle());
			notification.setUsername(blog.getPostedBy().getUsername()); 
			if (blog.isApproved()) {
										
				session.update(blog);							
				notification.setApprovalstatus("Approved");
				session.save(notification);
			} else {			
				System.out.println(rejectedReason);
				if (rejectedReason.equals(""))
					notification.setRejectedreason("Not Mentioned by Admin");
				else
					notification.setRejectedreason(rejectedReason);
				notification.setApprovalstatus("Rejected");
				session.save(notification);
				session.delete(blog);

			}
		}
		public void addcomment(BlogComment blogcomment) {
			// TODO Auto-generated method stub
			
		}
	}

