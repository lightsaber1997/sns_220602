package com.sns.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/user_list")
	public List<User> userList() {
		return userBO.getUserList();
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		
		
		
		User user = userBO.getUser(loginId, password);
		
		
		if (user != null) {			
			// destroy the old existing session if it exists
			// this part of the code tries to defend the session fixation attack.
			// But I'm not sure if this code works properly, so don't rest on it.
			HttpSession session = request.getSession(false);
			if (session!= null && !session.isNew()) {
				session.invalidate();
			}
			// create new session
			request.getSession(true);
			
			// add appropriate attributes to the session
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			result.put("success", true);
		}
		
		else {
			result.put("success", false);
		}
		
		
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		Map<String, Object> result = new HashMap<>();
		try {
			userBO.addUser(loginId, password, name, email);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		
		return result;
	}
	
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			if (userBO.getUserByLoginId(loginId) != null) {
				result.put("is_duplicate", true);
			}
			
			else {
				result.put("is_duplicate", false);
			}
			
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		
		
		
		
		return result;
	}
	
	@RequestMapping("/sign_out")
	public void signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
}
