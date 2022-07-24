package com.sns.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserDAO {
	public List<User> selectUserList();
	public User selectUserByLoginId(
			@Param("loginId") String loginId);
	public void insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email);
	public User selectUserById(
			@Param("userId") int userId);
}
