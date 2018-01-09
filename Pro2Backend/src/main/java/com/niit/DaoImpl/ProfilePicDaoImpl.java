package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.Dao.ProfileDao;
import com.niit.model.ProfilePic;


@Repository
@Transactional
public class ProfilePicDaoImpl implements ProfileDao {
	
	@Autowired
	private SessionFactory sessionf;
	public void saveOrUpdate(ProfilePic profilePic) {
		
		Session session=sessionf.getCurrentSession();
		session.saveOrUpdate(profilePic);
		
	}
	public ProfilePic getprofilepic(String username) {
		
		Session session=sessionf.getCurrentSession();
		ProfilePic profilepic=(ProfilePic)session.get(ProfilePic.class, username);
		return profilepic;
	}

}