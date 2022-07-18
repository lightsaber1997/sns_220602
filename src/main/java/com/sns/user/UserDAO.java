package com.sns.user;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
	public List<User> selectUserList();
}
