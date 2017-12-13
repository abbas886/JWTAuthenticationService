package com.stackroute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activity.dao.UserDAO;
import com.stackroute.activity.model.User;

@RestController()
@RequestMapping("api/user")
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> users = userDAO.list();
		if(users==null || users.isEmpty())
		{
			return  new ResponseEntity<>(users,HttpStatus.NO_CONTENT);
		}
		return  new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserDetails(@PathVariable("id") String id)
	{
		user = userDAO.get(id);
		if(user==null )
		{
			return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<>(user,HttpStatus.FOUND);
	}
	

	
	
}
