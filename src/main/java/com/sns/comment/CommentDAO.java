package com.sns.comment;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface CommentDAO {
	public void insertComment(
			@RequestParam("postId") int postId, 
			@RequestParam("userId") int userId, 
			@RequestParam("content") String content);
}
