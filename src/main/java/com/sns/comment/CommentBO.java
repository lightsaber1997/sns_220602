package com.sns.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	
	public void insertComment(int postId, int userId, String content) {
		commentDAO.insertComment(postId, userId, content);
	}
}
