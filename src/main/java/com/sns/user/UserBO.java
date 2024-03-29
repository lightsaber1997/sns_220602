package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	@Qualifier("BCrypt")
	private PasswordEncoder passwordEncoder;
	
	public List<User> getUserList() {
		return userDAO.selectUserList();
	}
	
	public User getUserByLoginId(String loginId) {
		return userDAO.selectUserByLoginId(loginId);
	}
	
	public void addUser(String loginId,  String password,
			 String name, String email) {
		String hashedPassword = passwordEncoder.encode(password);
		userDAO.insertUser(loginId, hashedPassword, name, email);
	}
	
	public User getUser(String loginId, String password) {
		try {
			User user = this.getUserByLoginId(loginId);
			if (user == null) {
				return null;
			}
			
			if (passwordEncoder.matches(password, user.getPassword())) {
				return user;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public User getUserById(int userId) {
		return userDAO.selectUserById(userId);
	}
}
