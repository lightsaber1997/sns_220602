package com.sns.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
	public List<User> selectUserList();
	public User selectUserByLoginId(
			@Param("loginId") String loginId);
}
