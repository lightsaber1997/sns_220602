package com.sns.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO {
	public void insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("content") String content);
	public List<Comment> selectCommentByPostId(@Param("postId") int postId);
	public List<CommentView> selectCommentViewByPostId(
			@Param("postId") int postId);
	
}
