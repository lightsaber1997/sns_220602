package com.sns.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/sign_up")
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
}
