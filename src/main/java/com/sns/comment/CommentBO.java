package com.sns.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	
	public void insertComment(int postId, int userId, String content) {
		commentDAO.insertComment(postId, userId, content);
	}
	
	public List<Comment> selectCommentByPostId(int postId) {
		return commentDAO.selectCommentByPostId(postId);
	}
	
	public List<CommentView> selectCommentViewByPostId(int postId) {
		return commentDAO.selectCommentViewByPostId(postId);
	}
}
