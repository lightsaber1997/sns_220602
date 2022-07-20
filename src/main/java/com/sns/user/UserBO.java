package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public List<User> getUserList() {
		return userDAO.selectUserList();
	}
	
	public User getUserByLoginId(String loginId) {
		return userDAO.selectUserByLoginId(loginId);
	}
	
	public void addUser(String loginId,  String password,
			 String name, String email) {
		// TODO
		String hashedPassword = "dd";
		userDAO.insertUser(loginId, password, name, email);
	}

}
