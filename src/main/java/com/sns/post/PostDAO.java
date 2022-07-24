package com.sns.post;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	public List<Post> selectAll();
	public Post selectById(
			@Param("id") int id);
	public void insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
}
