package com.niit.Dao;

import com.niit.model.ProfilePic;

public interface ProfileDao {

	void saveOrUpdate(ProfilePic profilePic);
	ProfilePic getprofilepic(String username);
	
}