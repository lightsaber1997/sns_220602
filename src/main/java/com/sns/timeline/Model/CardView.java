package com.sns.timeline.Model;

import java.util.List;

import com.sns.comment.Comment;
import com.sns.comment.CommentView;
import com.sns.post.Post;
import com.sns.user.User;

public class CardView {
	private Post post;
	private User user;
	private List<CommentView> commentList;
	
	
	public CardView(Post post, User user, List<CommentView> commentList) {
		super();
		this.post = post;
		this.user = user;
		this.commentList = commentList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}
	
	
	
	
	
}
