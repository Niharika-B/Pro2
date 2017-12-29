package com.niit.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	
	public UserController(){
		System.out.println("UserController is Instantiated");
	}
	
	//client -Angular JS Client
	//User - in JSON
	//convert JSON to java object
	// ? any type, for success Type is User, for error Type is ErrorClazz
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){//5 //@RequestBody will convert the json to java objects
		try{
			if(!userDao.isUsernameValid(user.getUsername())){//Duplicate Username
				ErrorClazz error=new ErrorClazz(2,"Username already exists..please choose different username");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
			}
			if(!userDao.isEmailValid(user.getEmail())){
				ErrorClazz error=new ErrorClazz(3,"EmailId already exists..please enter different email address");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
			}
		   userDao.registerUser(user);
		}catch(Exception e){
			ErrorClazz error=new ErrorClazz(1,"Unable to register user details");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			//failure - response.data=error, response.status=500			
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		User validUser=userDao.login(user);
		if(validUser==null){
			ErrorClazz errorClazz=new ErrorClazz(4,"Invalid username/password");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//ErrorClazz,401
		}
		else{
			validUser.setOnline(true);
			session.setAttribute("username", validUser.getUsername());
			userDao.update(validUser);
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){//without login
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);//401 -> login.html
		}
		User user=userDao.getUserByUsername(username);//select * from user where username=?
		user.setOnline(false);
		userDao.update(user);//update User set online=false where username=?
		session.removeAttribute("username");
		session.invalidate();
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);	
		}
		User user=userDao.getUserByUsername(username);
		return new ResponseEntity<User>(user,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/updateuser",method=RequestMethod.GET)
	public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session){
		if(session.getAttribute("username")==null){
    		ErrorClazz error=new ErrorClazz(5,"UnAuthorized User");
    		return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
    	}
    	try{
    	userDao.update(user);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    	}catch(Exception e){
    		ErrorClazz error=new ErrorClazz(6,"Unable to edit user profile "+ e.getMessage());
    		return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

}