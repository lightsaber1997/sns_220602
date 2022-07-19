package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/is_duplicated_id")
	public boolean isDuplicatedId() {
		
	}
}
