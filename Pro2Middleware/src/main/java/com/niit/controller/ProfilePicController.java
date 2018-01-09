package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.Dao.ProfileDao;
import com.niit.model.ErrorClazz;
import com.niit.model.ProfilePic;



@RestController

public class ProfilePicController {

	@Autowired
	private ProfileDao profile;

	@RequestMapping(value = "/updateprofilepic", method = RequestMethod.POST)
	public ResponseEntity<?> updateorsave(@RequestParam CommonsMultipartFile image, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		ProfilePic profilep = new ProfilePic();
		profilep.setImage(image.getBytes());
		profilep.setUsername(username);
		try {
			profile.saveOrUpdate(profilep);
			return new ResponseEntity<ProfilePic>(profilep, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ErrorClazz error = new ErrorClazz(6, "unable to upload");
			System.out.println(e);
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getimage/{username}", method = RequestMethod.GET)
	public @ResponseBody byte[] getprofilepic(@PathVariable String username, HttpSession session) {
		String currentuser = (String) session.getAttribute("username");
		if (currentuser == null) {
			return null;
		}
		ProfilePic profilep = profile.getprofilepic(username);
		if (profilep == null)
			return null;
		else
			return profilep.getImage();
	}
}
